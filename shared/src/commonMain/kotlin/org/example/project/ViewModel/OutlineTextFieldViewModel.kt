package org.example.project.ViewModel

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import org.example.project.xlsxHandlers.copyTemplateTo
import org.example.project.xlsxHandlers.replacePlaceholders
import org.example.project.xlsxHandlers.selectDirectory
import java.io.File
import java.time.LocalTime
import kotlin.collections.List

class OutlineTextFieldViewModel: ViewModel() {
    val constValue = ConstData("","","",)

    private var _inputText = MutableStateFlow<List<InputData>>(
        mutableListOf(InputData(false, "","", "","" ,"","")
        ))
    val inputText: StateFlow<List<InputData>> = _inputText.asStateFlow()

    fun createInput() {
        _inputText.value = _inputText.value + _inputText.value.last()
    }

    fun updateInputDate(it: Int, newText: String) {
        val currentList =_inputText.value.toMutableList()
        currentList[it] = currentList[it].copy(date = newText)
        _inputText.value = currentList
    }
    fun updateInputProjectSection(it: Int, newProjectSection: String) {
        val currentList = _inputText.value.toMutableList()
        currentList[it] = currentList[it].copy(projectSection = newProjectSection)
        _inputText.value = currentList
    }
    fun updateInputNumberOfTheWeldedJoint(it: Int, newText: String) {
        val currentList =_inputText.value.toMutableList()
        currentList[it] = currentList[it].copy(numberOfTheWeldedJoint = newText)
        _inputText.value = currentList
    }
    fun updateInputDimeterAndThickness(it: Int, newText: String) {
        val currentList =_inputText.value.toMutableList()
        currentList[it] = currentList[it].copy(dimeterAndThickness = newText)
        _inputText.value = currentList
    }
    fun updateInputSteelGrade(it: Int, newText: String) {
        val currentList =_inputText.value.toMutableList()
        currentList[it] = currentList[it].copy(steelGrade = newText)
        _inputText.value = currentList
    }

    fun updateInputTime(it: Int, newText: String) {
        val currentList =_inputText.value.toMutableList()
        currentList[it] = currentList[it].copy(time = newText)
        _inputText.value = currentList
    }

    fun updateOnCheckedState(it:Int, newCheck: Boolean){
        val currentList = _inputText.value.toMutableList()
        currentList[it] = currentList[it].copy(onChecked = newCheck)
        _inputText.value = currentList
    }

    //Getter для получения значения из конкретного элемента в списке
    fun getInputText(i: Int): InputData {
        return _inputText.value[i]
    }

    fun removeItem(){
        val currentList = _inputText.value.toMutableList()
        currentList.removeAt(_inputText.value.size - 1)
        _inputText.value = currentList
    }

    fun removeAll(){
        val currentList = _inputText.value.toMutableList()
        currentList.clear()
        currentList.add(InputData(false, "","", "","" ,"",""))
        _inputText.value = currentList
    }

    fun removeSelectedItem(){
        val currentList = mutableListOf<InputData>()
        for (i in _inputText.value){
            if (!i.onChecked){
                currentList.add(i)
            }
        }
        _inputText.value = currentList
    }

    fun addXlsx(){

        for (i in _inputText.value) {
            val listTime = mutableListOf<String>()
            val m = i.time.split(":")
            val z1 = m[0].toInt()
            val z2 = m[1].toInt()
            var metingTime = LocalTime.of(z1,z2)
            listTime.add("$metingTime")

            val firstValue = metingTime.plusMinutes(15)
            listTime.add("$firstValue")
            metingTime = firstValue

            for (i in 2..45){
                val newValue = metingTime.plusMinutes(7)
                listTime.add("$newValue")
                metingTime = newValue
            }

            val folder = selectDirectory()
            if (folder == null) {
                println("Папка не выбрана")
                return
            }

            // 2. Копируем шаблон (создаст asas.xlsx)
            val copiedFile = copyTemplateTo(folder, "${i.numberOfTheWeldedJoint}_copy")
            if (!copiedFile.exists()) {
                println("Ошибка: файл не скопирован")

            }

            // 3. Даём время на запись (опционально)
            Thread.sleep(500)

            // 4. Создаём выходной файл (с другим именем, чтобы не портить шаблон)
            val outputFile = File(folder, "${i.numberOfTheWeldedJoint}_final.xlsx")

            replacePlaceholders(copiedFile, outputFile, i, listTime, constValue)

            Thread.sleep(500)
        }
    }
}

