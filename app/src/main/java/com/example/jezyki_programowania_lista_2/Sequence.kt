package com.example.jezyki_programowania_lista_2

abstract class Sequence {
    var identifier:String
    var data:String
    val VALID_CHARS:List<Char>
    var length: Int

    constructor(new_identifier:String, new_data:String, new_valid_chars:List<Char>){
        identifier = new_identifier
        data = new_data
        length = data.length
        VALID_CHARS = new_valid_chars

        for (item in data) {
            if (!VALID_CHARS.contains(item)) {
                throw IllegalArgumentException("Sekwencja zawiera niedozwolone znaki: $item")
            }
        }

    }

    override fun toString():String{
        return ">$identifier\n$data"
    }

    fun mutate(position:Int, value:Char){

        if (position > length || position <= 0) {
            throw IllegalArgumentException("Pozycja $position jest poza granicami [1 : $length]")
        }
        if (!VALID_CHARS.contains(value)) {
            throw IllegalArgumentException("Znak: $value nie jest dopusczalny")
        }
        data = data.substring(0, position - 1) + value + data.substring(position)
    }

    fun findMotif(motif:String): MutableList<Int>{
        var wystapienia:MutableList<Int> = mutableListOf()
        for(i in 0..motif.length - 1){
            var index = data.indexOf(motif, i)
            if (index != -1 ){
                wystapienia.add(i)
            }
        }
        return wystapienia
    }
}