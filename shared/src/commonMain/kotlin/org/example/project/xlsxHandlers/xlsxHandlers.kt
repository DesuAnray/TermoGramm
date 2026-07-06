package org.example.project.xlsxHandlers

import androidx.compose.runtime.isTraceInProgress
import org.apache.poi.ss.usermodel.CellType
import org.apache.poi.ss.usermodel.WorkbookFactory
import org.example.project.ViewModel.ConstData
import org.example.project.ViewModel.InputData
import java.awt.FileDialog
import java.awt.Frame
import java.io.File
import java.nio.file.Files
import java.nio.file.StandardCopyOption
import javax.swing.JFileChooser


fun selectDirectory(): File? {
    val chooser = JFileChooser().apply {
        fileSelectionMode = JFileChooser.DIRECTORIES_ONLY
        dialogTitle = "Выберите папку"
    }
    return if (chooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
        chooser.selectedFile
    } else null
}


fun copyTemplateTo(targetFolder: File, nameFile: String): File{
    val targetFile = File(targetFolder,"$nameFile.xlsx")
    val inputStream = Thread.currentThread().contextClassLoader
        .getResourceAsStream("pattern.xlsx")
    try {
        Files.copy(inputStream, targetFile.toPath(), StandardCopyOption.REPLACE_EXISTING)
    }
    finally {
        // Обязательно закрываем поток, чтобы освободить ресурсы.
        inputStream.close()
    }
    return targetFile
}

fun replacePlaceholders(file: File, outputFile: File,
                        value: InputData, listTime: List<String>,
                        constValue: ConstData) {
    // 1. Открываем книгу

    val workbook = WorkbookFactory.create(file)
    try {
        // === НОВЫЙ БЛОК: замена {date} в BN1 ===
        val sheet = workbook.getSheetAt(0) // если маркер на первом листе
        val colIndex = columnIndexFromLetter("BJ")  // было BN
        val rowIndex = 0  // строка 1
        var row = sheet.getRow(rowIndex)
        if (row == null) row = sheet.createRow(rowIndex)
        var cell = row.getCell(colIndex)
        if (cell == null) cell = row.createCell(colIndex)
        if (cell.cellType == CellType.STRING) {
            val oldText = cell.stringCellValue
            val newText = oldText.replace("{date}", "DATE:${value.date}" ?: "")
            if (newText != oldText) {
                cell.setCellValue(newText)
            }
        } else {
            cell.setCellValue(value.date ?: "")
        }
        // ========================================

        // Дальше ваш существующий цикл
        for (sheetIdx in 0 until workbook.numberOfSheets) {
            val sheet = workbook.getSheetAt(sheetIdx)
            for (rowIdx in 0 until sheet.physicalNumberOfRows) {
                val row = sheet.getRow(rowIdx) ?: continue
                for (cellIdx in 0 until row.physicalNumberOfCells) {
                    val cell = row.getCell(cellIdx) ?: continue
                    if (cell.cellType == CellType.STRING) {
                        var text = cell.stringCellValue
                        // заменяем только остальные маркеры (date уже заменён)
                        text = text.replace("{projectSection}", constValue.diagram )
                        text = text.replace("{object}", constValue.objectControl )
                        text = text.replace("{Supervisor}", "Руководитель работ: ${constValue.supervisor}/______________" )
                        text = text.replace("{Operator}", "Оператор термист: ${constValue.operator}/______________" )
                        text = text.replace("{numberOfTheWeldedJoint}", value.numberOfTheWeldedJoint )
                        text = text.replace("{dimeterAndThickness}", value.dimeterAndThickness)
                        text = text.replace("{steelGrade}", value.steelGrade)


                        for (i in 0..45){
                            text = text.replace("{time$i}", listTime[i])
                        }
                        if (text != cell.stringCellValue) {
                            cell.setCellValue(text)
                        }
                    }
                }
            }
        }

        workbook.setForceFormulaRecalculation(true)
        outputFile.outputStream().use { workbook.write(it) }
    } finally {
        workbook.close()
    }
}

fun columnIndexFromLetter(columnLetter: String): Int {
    var index = 0
    for (ch in columnLetter.uppercase()) {
        index = index * 26 + (ch - 'A' + 1)
    }
    return index - 1  // POI индексы с 0
}