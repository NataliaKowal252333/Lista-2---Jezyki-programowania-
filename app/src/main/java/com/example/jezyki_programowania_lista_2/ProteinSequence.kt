package com.example.jezyki_programowania_lista_2

class ProteinSequence {
    var identifier:String
    var data:String
    val VALID_CHARS:List<Char> = listOf('A', 'T', 'C', 'G')
    var length: Int

    constructor(new_identifier:String, new_data:String){
        identifier = new_identifier
        data = new_data
        length = data.length
    }


    override fun toString():String{
        return ">$identifier\n$data"
    }

    fun mutate(position:Int, value:Char){
        data = data.substring(0, position) + value + data.substring(position + 1)

    }
    fun findMotif(motif:String): Int{
        return data.indexOf(motif)
    }
}