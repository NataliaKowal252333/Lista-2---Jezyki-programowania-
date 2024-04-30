package com.example.jezyki_programowania_lista_2

class RNASequence:Sequence {
    // kodonyNaAminokwasy zostały wygenerowane za pomocą modelu językowego
    val kodonyNaAminokwasy = mapOf(
        // Alanina
        "GCT" to "A", "GCC" to "A", "GCA" to "A", "GCG" to "A",
        // Arginina
        "CGT" to "R", "CGC" to "R", "CGA" to "R", "CGG" to "R", "AGA" to "R", "AGG" to "R",
        // Asparagina
        "AAT" to "N", "AAC" to "N",
        // Kwas asparaginowy
        "GAT" to "D", "GAC" to "D",
        // Cysteina
        "TGT" to "C", "TGC" to "C",
        // Glutamina
        "CAA" to "Q", "CAG" to "Q",
        // Kwas glutaminowy
        "GAA" to "E", "GAG" to "E",
        // Glicyna
        "GGT" to "G", "GGC" to "G", "GGA" to "G", "GGG" to "G",
        // Histydyna
        "CAT" to "H", "CAC" to "H",
        // Izoleucyna
        "ATT" to "I", "ATC" to "I", "ATA" to "I",
        // Leucyna
        "CTT" to "L", "CTC" to "L", "CTA" to "L", "CTG" to "L", "TTA" to "L", "TTG" to "L",
        // Lizyna
        "AAA" to "K", "AAG" to "K",
        // Metionina (start)
        "ATG" to "M",
        // Fenyloalanina
        "TTT" to "F", "TTC" to "F",
        // Prolina
        "CCT" to "P", "CCC" to "P", "CCA" to "P", "CCG" to "P",
        // Seryna
        "TCT" to "S", "TCC" to "S", "TCA" to "S", "TCG" to "S", "AGT" to "S", "AGC" to "S",
        // Treonina
        "ACT" to "T", "ACC" to "T", "ACA" to "T", "ACG" to "T",
        // Tryptofan
        "TGG" to "W",
        // Tyrozyna
        "TAT" to "Y", "TAC" to "Y",
        // Walina
        "GTT" to "V", "GTC" to "V", "GTA" to "V", "GTG" to "V"
    )

    constructor(new_identifier:String, new_data:String):super(new_identifier,new_data, listOf('A', 'U', 'C', 'G')){
    }

    fun isStartKodon(kodon: String): Boolean {
        return kodon == "AUG"
    }

    fun isStopKodon(kodon: String): Boolean {
        return kodon in listOf("UAA","UAG","UGA" )
    }
    fun transcribe():RNASequence{
        var i:Int = 0
        for(element in data){
             if(isStartKodon(data.substring(i, i+3))){
                 // Jesli zostanie znaleziony kodon "start" rozpoczyna sie proces transkrypcji

             }
        }

    }
}