package org.datepicker.utils.readExcel;

import org.apache.commons.lang3.StringUtils;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;

import java.io.*;
import java.util.*;

public class dataToFeature {

    private static List<String> setExcelDataToFeature(File featureFile)
            throws InvalidFormatException, IOException {

        List<String> fileData = new ArrayList<>();
        try (BufferedReader buffReader = new BufferedReader(
                new InputStreamReader(new BufferedInputStream(new FileInputStream(featureFile)), "UTF-8"))) {

            String line;
            List<Map<String, String>> excelData = null;
            boolean foundHashTag = false;
            boolean featureData = false;

            while ((line = buffReader.readLine()) != null) {
                String sheetName = null;
                String excelFilePath = null;
                Map<String, String> filters = new LinkedHashMap<>();

                // Detectar el marcador y extraer ruta y pestaña
                if (line.trim().contains("##@externaldata")) {
                    // Formato esperado: ##@externaldata@<ruta>@<hoja>[@<filtro1>=<valor1>[@<filtro2>=<valor2>]...]
                    String trimmed = line.trim();
                    String[] tokens = trimmed.split("@");
                    // tokens: ["##", "externaldata", "<ruta>", "<hoja>", "<filtro>..."]
                    if (tokens.length >= 4) {
                        excelFilePath = tokens[2];
                        sheetName = tokens[3];
                        // Parsear filtros opcionales desde el índice 4 en adelante
                        for (int i = 4; i < tokens.length; i++) {
                            String t = tokens[i];
                            if (StringUtils.isBlank(t)) continue;
                            int eq = t.indexOf('=');
                            if (eq > 0 && eq < t.length() - 1) {
                                String key = t.substring(0, eq).trim();
                                String val = t.substring(eq + 1).trim();
                                if (StringUtils.isNotBlank(key)) {
                                    filters.put(key, val);
                                }
                            }
                        }
                    } else {
                        // Retrocompatibilidad (aunque no debería ocurrir)
                        excelFilePath = line.substring(
                                StringUtils.ordinalIndexOf(line, "@", 2) + 1,
                                line.lastIndexOf("@"));
                        sheetName = line.substring(line.lastIndexOf("@") + 1);
                    }
                    foundHashTag = true;
                    fileData.add(line);
                }

                // Si acabamos de encontrar el marcador, cargar y filtrar Excel
                if (foundHashTag) {
                    excelData = new readExcelFile().getData(excelFilePath, sheetName);

                    // 1) Filtrar filas completamente vacías
                    List<Map<String, String>> nonEmptyRows = new ArrayList<>();
                    for (Map<String, String> row : excelData) {
                        boolean allBlank = true;
                        for (String v : row.values()) {
                            if (StringUtils.isNotBlank(v)) {
                                allBlank = false;
                                break;
                            }
                        }
                        if (!allBlank) {
                            nonEmptyRows.add(row);
                        }
                    }
                    excelData = nonEmptyRows;

                    // 1b) Aplicar filtros opcionales (por ejemplo: caseId=1)
                    if (!filters.isEmpty()) {
                        List<Map<String, String>> filtered = new ArrayList<>();
                        for (Map<String, String> row : excelData) {
                            boolean match = true;
                            for (Map.Entry<String, String> f : filters.entrySet()) {
                                String val = row.getOrDefault(f.getKey(), "");
                                if (!Objects.equals(val, f.getValue())) {
                                    match = false;
                                    break;
                                }
                            }
                            if (match) filtered.add(row);
                        }
                        excelData = filtered;
                    }

                    // 2) Determinar columnas con al menos un valor
                    List<String> keysToKeep = new ArrayList<>();
                    if (!excelData.isEmpty()) {
                        for (String key : excelData.get(0).keySet()) {
                            for (Map<String, String> row : excelData) {
                                if (StringUtils.isNotBlank(row.get(key))) {
                                    keysToKeep.add(key);
                                    break;
                                }
                            }
                        }
                    }

                    // 3) Construir líneas para cada fila y columna válida
                    for (Map<String, String> row : excelData) {
                        StringBuilder cellData = new StringBuilder();
                        for (String key : keysToKeep) {
                            cellData.append("|")
                                    .append(Optional.ofNullable(row.get(key)).orElse(""));
                        }
                        cellData.append("|");
                        fileData.add(cellData.toString());
                    }

                    foundHashTag = false;
                    featureData = true;
                    continue;
                }

                // Evitar líneas de tabla residuales tras insertar datos
                if (line.startsWith("|") || line.endsWith("|")) {
                    if (featureData) {
                        // salto líneas vacías de tabla
                        continue;
                    } else {
                        fileData.add(line);
                        continue;
                    }
                } else {
                    featureData = false;
                }

                fileData.add(line);
            }
        }

        return fileData;
    }

    private static List<File> listOfFeatureFiles(File folder) {
        List<File> featureFiles = new ArrayList<>();
        for (File entry : Objects.requireNonNull(folder.listFiles())) {
            if (entry.isDirectory()) {
                featureFiles.addAll(listOfFeatureFiles(entry));
            } else if (entry.getName().endsWith(".feature")) {
                featureFiles.add(entry);
            }
        }
        return featureFiles;
    }

    public static void overrideFeatureFiles(String featuresDirectoryPath)
            throws IOException, InvalidFormatException {

        List<File> files = listOfFeatureFiles(new File(featuresDirectoryPath));
        for (File featureFile : files) {
            List<String> updated = setExcelDataToFeature(featureFile);
            try (BufferedWriter writer = new BufferedWriter(
                    new OutputStreamWriter(new FileOutputStream(featureFile), "UTF-8"))) {

                for (String l : updated) {
                    writer.write(l);
                    writer.write("\n");
                }
            }
        }
    }
}