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
    }

    override fun toString():String{
        return ">$identifier\n$data"
    }

    fun mutate(position:Int, value:Char){
        data = data.substring(0, position) + value + data.substring(position + 1)
    }

    fun findMotif(motif:String): MutableList<Int>{
        var wystapienia:MutableList<Int> = mutableListOf()
    }
}