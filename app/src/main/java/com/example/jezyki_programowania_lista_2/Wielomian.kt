package com.example.jezyki_programowania_lista_2

import kotlin.test.Test
import kotlin.test.assertEquals

// Zadanie 1
class Wielomian {

    var wspolczynniki:MutableList<Double> = mutableListOf()
    constructor(nowe_wspolczynniki: MutableList<Double>){
        if (nowe_wspolczynniki.isEmpty()) {
            throw IllegalArgumentException("Lista współczynników nie może być pusta.")
        }
        for (i in nowe_wspolczynniki.size -1 downTo 0){
            if (nowe_wspolczynniki[i] != 0.0){
                wspolczynniki = nowe_wspolczynniki.subList(0,i+1)
                break
            }
        }
    }

    fun stopien_wielomianu():Int{
        return wspolczynniki.size - 1
    }

    fun tekstowa_reprezentacja():String{
        var stopien:Int = wspolczynniki.size - 1
        var reprezentacja:String = "W(x) = "
        for(wspolczynnik in wspolczynniki.reversed()){
           if(stopien == 1){
               reprezentacja += wspolczynnik.toString() + "x + "
           }
            else if(stopien == 0 ) {
               reprezentacja += wspolczynnik.toString()
           }
            else {
               reprezentacja += wspolczynnik.toString() + "x^" + stopien.toString() + " + "
           }
            stopien --
        }
        return reprezentacja
    }
    operator fun invoke(x: Double): Double {
        var stopien:Int = wspolczynniki.size - 1
        var suma:Double = 0.0
        for(wspolczynnik in wspolczynniki.reversed()){
            suma += Math.pow(x, stopien.toDouble()) * wspolczynnik
            stopien --
        }
        return suma
    }

    operator fun plus(inny: Wielomian): Wielomian {
        val noweWspolczynniki = mutableListOf<Double>()
        val maxLength = maxOf(wspolczynniki.size, inny.wspolczynniki.size)
        for (i in 0 until maxLength) {
            val thisVal = wspolczynniki.getOrElse(i) { 0.0 }
            val otherVal = inny.wspolczynniki.getOrElse(i) { 0.0 }
            noweWspolczynniki.add(thisVal + otherVal)
        }
        return Wielomian(noweWspolczynniki)
    }

    operator fun minus (inny: Wielomian): Wielomian {
        val noweWspolczynniki = mutableListOf<Double>()
        val maxLength = maxOf(wspolczynniki.size, inny.wspolczynniki.size)
        for (i in 0 until maxLength) {
            val thisVal = wspolczynniki.getOrElse(i) { 0.0 }
            val otherVal = inny.wspolczynniki.getOrElse(i) { 0.0 }
            noweWspolczynniki.add(thisVal - otherVal)
        }
        return Wielomian(noweWspolczynniki)
    }
    operator fun times(mnoznik: Double): Wielomian{
        val nowe_wspolczynniki:MutableList<Double> = mutableListOf()
        for(wspolczynnik in wspolczynniki){
            nowe_wspolczynniki.add(wspolczynnik*mnoznik)
        }
        return Wielomian(nowe_wspolczynniki)
    }
    operator fun times(inny: Wielomian): Wielomian {
        val noweWspolczynniki = MutableList(stopien_wielomianu() + inny.stopien_wielomianu() + 1) { 0.0 }
        for (i in wspolczynniki.indices) {
            for (j in inny.wspolczynniki.indices) {
                noweWspolczynniki[i + j] += wspolczynniki[i] * inny.wspolczynniki[j]
            }
        }
        return Wielomian(noweWspolczynniki)
    }
}

fun main (){ // kod prezentujący funkcjonalność klasy Wielomian
    var W1: Wielomian = Wielomian(mutableListOf(1.0, 2.0, 3.0, 4.0))
    println(W1.tekstowa_reprezentacja())

    var W2: Wielomian = Wielomian(mutableListOf(6.0, 5.0, 7.0))
    println(W2.tekstowa_reprezentacja())

    var W3: Wielomian = W1 - W2
    println(W3.tekstowa_reprezentacja())

    var W4: Wielomian = W1 + W2
    println(W4.tekstowa_reprezentacja())

    var W5: Wielomian = W1 * 6.0
    println(W5.tekstowa_reprezentacja())

    var W6: Wielomian = W1 * W2
    println(W6.tekstowa_reprezentacja())
}

class wielomianTests {
    @Test
    fun testWywolania() {
        val wielomian = Wielomian(mutableListOf(2.0, 3.0, 1.0)) // x^2 + 3x + 2
        val wynik = wielomian(2.0) // Powinno zwrócić 2 + 3*2 + 2^2 = 12
        println("Wielomian: ${wielomian.tekstowa_reprezentacja()} dla wartosci x = 2.0 wynosi: $wynik")
        assertEquals(12.0, wynik, 0.001)
    }

    @Test
    fun testDodawania() {
        val W1 = Wielomian(mutableListOf(2.0, 3.0, 1.0)) // x^2 + 3x + 2
        val W2 = Wielomian(mutableListOf(10.0, 8.0)) // 8x + 10
        val W3 = W1 + W2 // x^2 +11x + 12
        val wynik:Double = W3(2.0) // Powinno zwrócić 38
        println("Wielomian: ${W3.tekstowa_reprezentacja()} dla wartosci x = 2.0 wynosi: $wynik")
        assertEquals(38.0, wynik, 0.001)
    }

    @Test
    fun testOdejmowania() {
        val W1 = Wielomian(mutableListOf(2.0, 3.0, 1.0)) // x^2 + 3x + 2
        val W2 = Wielomian(mutableListOf(10.0, 8.0)) // 8x + 10
        val W3 = W1 - W2 // x^2 - 5x - 8
        val wynik:Double = W3(2.0) // Powinno zwrócić -14
        println("Wielomian: ${W3.tekstowa_reprezentacja()} dla wartosci x = 2.0 wynosi: $wynik")
        assertEquals(-14.0, wynik, 0.001)
    }

    @Test
    fun testMnozeniaSkalarnego() {
        val W1 = Wielomian(mutableListOf(2.0, 3.0, 1.0)) // x^2 + 3x + 2
        val W3 = W1 * 2.0 // 2x^2 + 6x + 4
        val wynik:Double = W3(2.0) // Powinno zwrócić 24
        println("Wielomian: ${W3.tekstowa_reprezentacja()} dla wartosci x = 2.0 wynosi: $wynik")
        assertEquals(24.0, wynik, 0.001)
    }

    @Test
    fun testMnozeniaWektorowego() {
        val W1 = Wielomian(mutableListOf(2.0, 3.0, 1.0)) // x^2 + 3x + 2
        val W2 = Wielomian(mutableListOf(10.0, 8.0)) // 8x + 10
        val W3 = W1 * W2 // 8x^3 + 34x^2 + 46x + 20
        val wynik:Double = W3(2.0) // Powinno zwrócić 312
        println("Wielomian: ${W3.tekstowa_reprezentacja()} dla wartosci x = 2.0 wynosi: $wynik")
        assertEquals(312.0, wynik, 0.001)
    }

    @Test
    fun testStopienWielomianu (){
        val W1 = Wielomian(mutableListOf(2.0, 3.0, 1.0)) // x^2 + 3x + 2
        val wynik: Int = W1.stopien_wielomianu()
        println("Wielomian: ${W1.tekstowa_reprezentacja()} ")
        assertEquals(2, wynik)
    }

}