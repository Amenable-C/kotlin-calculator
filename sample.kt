package com.example.kotlinpractice

import kotlin.math.absoluteValue
import kotlin.reflect.typeOf

fun main(){
    println("하루 권장 칼로리 : ${recommendedKcal(70, 80, 180)}")
    println("단백질, 지방, 탄수화물 : ${nutrientRate(70, 80, 2460)}")
    println("부족한 영양소 비율(탄수화물, 단백질, 지방)(%) : ${rateOfScarceNutrient(100, 500, 200)}")
    var scarceNutrientList:List<Int> = listOf(rateOfScarceNutrient(100, 500, 200).component1(), rateOfScarceNutrient(100, 500, 200).component2(), rateOfScarceNutrient(100, 500, 200).component3())
    println("비교를 위한 '부족한 영양소 비율(탄, 단, 지)(%)' 리스트 $scarceNutrientList")
    var foodNutrientList = listOf(30, 40, 30, "꿩불고기")  // 첫 번째 음식 리스트라고 가정
    println("비교를 위한 첫 번쨰 음식의 영양소 비율(탄, 단, 지)(%) $foodNutrientList")
    //println("'부족한 영양소'와 '각 음식의 영양소' 차이값 : ${comparingForTheBest (scarceNutrientList, foodNutrientList.filterIsInstance<Int>())} (이 값이 작을수록 식단추천에 적합한 음식)")
    var differenceAndName = mutableMapOf<Int, String>()
    var differenceList = mutableListOf<Int>()
    differenceAndName.put(comparingForTheBest (scarceNutrientList, foodNutrientList.filterIsInstance<Int>()), foodNutrientList[3].toString())
    differenceList.add(comparingForTheBest (scarceNutrientList, foodNutrientList.filterIsInstance<Int>()))


    foodNutrientList = listOf(40, 30, 30, "닭갈비") // 두 번째 음식 리스트를 가져온 걸로 가정
    println("비교를 위한 두 번쨰 음식의 영양소 비율(탄, 단, 지)(%) $foodNutrientList")
    differenceAndName.put(comparingForTheBest (scarceNutrientList, foodNutrientList.filterIsInstance<Int>()), foodNutrientList[3].toString())
    differenceList.add(comparingForTheBest (scarceNutrientList, foodNutrientList.filterIsInstance<Int>()))

    foodNutrientList = listOf(22, 38, 40, "불고기") // 세 번째 음식 리스트를 가져온 걸로 가정
    println("비교를 위한 세 번쨰 음식의 영양소 비율(탄, 단, 지)(%) $foodNutrientList")
    differenceAndName.put(comparingForTheBest (scarceNutrientList, foodNutrientList.filterIsInstance<Int>()), foodNutrientList[3].toString())
    differenceList.add(comparingForTheBest (scarceNutrientList, foodNutrientList.filterIsInstance<Int>()))
    println("[차이값:음식 이름] : $differenceAndName")
    differenceList.sort() // 차이값 오름차순 정렬
    // 차이값을 key로 두고, 그에 해당하는 음식 찾기
    for(i in 0..2) {
        println("음식 추천 ${i}순위 : ${differenceAndName.get(differenceList[i])}")
    }


    //






//    println("차이값을 이용해서 우선 순위를 판단하기 위해 오름차순 정렬")
//    var ascendingData = differenceAndName.toSortedMap()
//    println(ascendingData)
//
//    println(ascendingData.values)
//    // 순서대로 뽑아야하는데, set으로 된거라서 깔끔하게 바로 안 나옴...
}

fun recommendedKcal(currentWeight: Int, targetWeight: Int, height: Int): Int {
    var standardWeight = (height - 100) * (0.9) * 30
    var forChange:Int = 0
    if(targetWeight > currentWeight) {
        forChange = 300
    }
    else if(targetWeight < currentWeight) {
        forChange = -500
    }



    var Kcal:Int = standardWeight.toInt() + forChange
    return Kcal
}

fun nutrientRate (currentWeight: Int, targetWeight: Int, Kcal: Int): Triple<Int, Int, Int> {
    var pro:Int = 0
    var fat = 0
    var cab = 0
    if(currentWeight == targetWeight){
        pro = (Kcal * (0.3)).toInt()
        fat = (Kcal * (0.2)).toInt()
        cab = (Kcal * (0.5)).toInt()
    }

    else if(currentWeight > targetWeight){
        pro = (Kcal * (0.4)).toInt()
        fat = (Kcal * (0.3)).toInt()
        cab = (Kcal * (0.3)).toInt()
    }

    else {
        pro = (Kcal * (0.4)).toInt()
        fat = (Kcal * (0.2)).toInt()
        cab = (Kcal * (0.4)).toInt()
    }
    return Triple(pro, fat, cab)
}

fun rateOfScarceNutrient (scarceCab:Int, scarcePro:Int, scarceFat: Int): Triple<Int, Int, Int> {
    var total = scarceCab + scarcePro + scarceFat
    var rateCab= scarceCab * 100 / total
    var ratePro = scarcePro * 100 / total
    var rateFat = scarceFat * 100 / total
    return Triple(rateCab, ratePro, rateFat)
}

fun comparingForTheBest (scarceNutrientList:List<Int>, foodNutrientList:List<Int>): Int{
    var difference = 0
    for(i: Int in 0..2){
        difference += (scarceNutrientList[i] - foodNutrientList[i]).absoluteValue
    }
    return difference
}


