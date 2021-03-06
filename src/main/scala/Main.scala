import lz.LZW
import statistic.{Huffman, ShannonFano}

class Test_RLE[T] {
  def test(msg : Seq[T]): Unit = {
    println(s"RLE on $msg:")
    val rle_compressor = new RLE[T]
    val compressed_data = rle_compressor.compress(msg)
    println(s"Compressed data: $compressed_data")
    val uncompressed_data = rle_compressor.uncompress(compressed_data)
    println(s"Uncompressed data: $uncompressed_data")
    if (uncompressed_data.isEmpty) {
      println("Empty entry data")
    } else {
      println(s"${if (uncompressed_data.get.equals(msg)) "OK" else "ko"}")
    }
    println()
  }
}


class Test_LZ {
  def test_lz78(msg : Seq[Char]) : Unit = {
    println(s"LZ78 on $msg:")
    val compressed_data = lz.LZ78.compress(msg)
    println(s"Compressed data: $compressed_data")
    val uncompressed_data = lz.LZ78.uncompress(compressed_data)
    println(s"Uncompressed data: $uncompressed_data")
    if (uncompressed_data.isEmpty) {
      println("Empty entry data")
    } else {
      println(s"${if (uncompressed_data.get.equals(msg)) "OK" else "ko"}")
    }
    println()
  }

  def test_lzw(msg : Seq[Char]) : Unit = {
    println(s"LZW on $msg:")
    val lzw_compressor = new LZW()
    val compressed_data = lzw_compressor.compress(msg)
    println(s"Compressed data: $compressed_data")
    val uncompressed_data = lzw_compressor.uncompress(compressed_data)
    println(s"Uncompressed data: $uncompressed_data")
    if (uncompressed_data.isEmpty) {
      println("Empty entry data")
    } else {
      println(s"${if (uncompressed_data.get.equals(msg)) "OK" else "ko"}")
    }
    println()
  }
}

class Test_Huffman[S] {
  def test(msg : Seq[S]): Unit = {
    println(s"Huffman on $msg:")
    val huffman_compressor = new Huffman[S](msg)
    val compressed_data = huffman_compressor.compress(msg)
    println(s"Compressed data: $compressed_data")
    val uncompressed_data = huffman_compressor.uncompress(compressed_data)
    println(s"Uncompressed data: $uncompressed_data")
    if (uncompressed_data.isEmpty) {
      println("Empty entry data")
    } else {
      println(s"${if (uncompressed_data.get.equals(msg)) "OK" else "ko"}")
    }
    println()
  }
}

class Test_ShannonFano[S] {
  def test(msg : Seq[S]): Unit = {
    println(s"Shannon Fano on $msg:")
    val sf_compressor = new ShannonFano[S](msg)
    val compressed_data = sf_compressor.compress(msg)
    println(s"Compressed data: $compressed_data")
    val uncompressed_data = sf_compressor.uncompress(compressed_data)
    println(s"Uncompressed data: $uncompressed_data")
    if (uncompressed_data.isEmpty) {
      println("Empty entry data")
    } else {
      println(s"${if (uncompressed_data.get.equals(msg)) "OK" else "ko"}")
    }
    println()
  }
}


object Main {
  def compare(msg: Seq[Char]): Unit = {
    val rle_compressor = new RLE[Char]
    val lzw_compressor = new LZW()
    val huffman_compressor = new Huffman[Char](msg)
    val sf_compressor = new ShannonFano[Char](msg)

    println(Seq((rle_compressor.compress(msg).size * 2 * 8, "RLE"),
      (lz.LZ78.compress(msg).size * 2 * 8, "LZ78"),
      (lzw_compressor.compress(msg).size * 9 + 256 * 8, "LZW"),
      (huffman_compressor.compress(msg).size, "Huffman"),
      (sf_compressor.compress(msg).size, "ShannonFano")).sortBy(_._1))
  }


  def main(args: Array[String]): Unit = {
    val empty_int_seq:Seq[Int] = IndexedSeq()
    val char_seq:Seq[Char] = Seq('b', 'e', 'l', 'l', 'e', ' ', 'e', 'c', 'h', 'e', 'l', 'l', 'e', ' ', '!')
    val char_seq1:Seq[Char] = Seq('b', 'e', 'l', 'l', 'e', 's', ' ', 'b', 'e', 'l', 'l', 'e', 's', ' ' , 'c', 'o', 'm', 'm', 'e', ' ', 'l', 'e', ' ', 'j', 'o', 'u', 'r', ' ', '!')
    val char_seq2:Seq[Char] = Seq('C', '\'', 'e', 's', 't', ' ', 'a', 's', 's', 'e', 'z', ',', ' ', 'd' , 'i', 't', ' ', 'l', 'a', ' ', 'b', 'a', 'l', 'e', 'i', 'n', 'e')
    val char_seq3:Seq[Char] = Seq('S', 'o', 'n', 't', '-', 'e', 'l', 'l', 'e', 's', ' ', 's', '??', 'c' , 'h', 'e', 's', ' ', '?')
    val char_seq4:Seq[Char] = Seq('C', 'e', ' ', 'p', 'a', 'l', 'e', ' ', 'p', 'a', 'l', 'o', 'i', 's' , ' ', 'e', 's', 't', ' ', 'e', 'm', 'p', 'a', 'l', 'e', ' ' , 's', 'u', 'r', ' ', 'u', 'n', ' ', 'p', 'a', 'l', 'e')
    val string_seq:Seq[String] = IndexedSeq("Scalapi", "Scalapi", "Scapali", "Poum", "Poum")
    val int_seq:Seq[Int] = IndexedSeq(1, 1, 1, 2, 4, 4, 5, 5, 3, 2, 3, 3)
    val seq_seq:Seq[Seq[Int]] = IndexedSeq(IndexedSeq(1, 2, 3), IndexedSeq(1, 2, 3), IndexedSeq(2))

    // RLE
    println("########## RLE ##########")
    val test_char = new Test_RLE[Char]
    val test_int = new Test_RLE[Int]
    val test_string = new Test_RLE[String]
    val test_seq_seq = new Test_RLE[Seq[Int]]

    test_int.test(empty_int_seq)
    test_char.test(char_seq)
    test_char.test(char_seq1)
    test_char.test(char_seq2)
    test_char.test(char_seq3)
    test_char.test(char_seq4)
    test_string.test(string_seq)
    test_int.test(int_seq)
    test_seq_seq.test(seq_seq)


    // TEST DICT
    val test_lz = new Test_LZ
    println("########## LZ78 ##########")
    test_lz.test_lz78(char_seq)
    test_lz.test_lz78(char_seq1)
    test_lz.test_lz78(char_seq2)
    test_lz.test_lz78(char_seq3)
    test_lz.test_lz78(char_seq4)

    println("########## LZW ##########")
    test_lz.test_lzw(char_seq)
    test_lz.test_lzw(char_seq1)
    test_lz.test_lzw(char_seq2)
    test_lz.test_lzw(char_seq3)
    test_lz.test_lzw(char_seq4)

    // TESTS STATS
    println("########## HUFFMAN ##########")
    val huffman_empty = new Test_Huffman[Int]
    huffman_empty.test(empty_int_seq)

    val huffman_char = new Test_Huffman[Char]
    huffman_char.test(char_seq)
    huffman_char.test(char_seq1)
    huffman_char.test(char_seq2)
    huffman_char.test(char_seq3)
    huffman_char.test(char_seq4)

    val huffman_string = new Test_Huffman[String]
    huffman_string.test(string_seq)

    val huffman_int = new Test_Huffman[Int]
    huffman_int.test(int_seq)

    val huffman_seq = new Test_Huffman[Seq[Int]]
    huffman_seq.test(seq_seq)

    println("########## SHANNON FANO ##########")
    val sf_empty = new Test_ShannonFano[Int]
    sf_empty.test(empty_int_seq)

    val sf_char = new Test_ShannonFano[Char]
    sf_char.test(char_seq)
    sf_char.test(char_seq1)
    sf_char.test(char_seq2)
    sf_char.test(char_seq3)
    sf_char.test(char_seq4)

    val sf_string = new Test_ShannonFano[String]
    sf_string.test(string_seq)

    val sf_int = new Test_ShannonFano[Int]
    sf_int.test(int_seq)

    val sf_seq = new Test_ShannonFano[Seq[Int]]
    sf_seq.test(seq_seq)


    println("########## COMPARISON ##########")
    compare("Une banane est tomb??e.")
    compare("Trois anneaux pour les Rois Elfes sous le ciel, Sept pour les Seigneurs Nains dans leurs demeures de pierre, Neuf pour les Hommes Mortels destin??s au tr??pas, Un pour le Seigneur des T??n??bres sur son sombre tr??ne Dans le Pays de Mordor o?? s\'??tendent les Ombres.")
    compare("Tu crois savoir tout ce qui se passe dans ton ??me, d??s que c'est suffisamment important, parce que ta conscience te l'apprendrait alors. Et quand tu restes sans nouvelles d'une chose qui est dans ton ??me, tu admets, avec une parfaite assurance, que cela ne s'y trouve pas. Tu vas m??me jusqu'?? tenir ?? psychique ?? pour identique ?? ?? conscient ??, c'est-??-dire connu de toi, et cela malgr?? les preuves les plus ??videntes qu'il doit sans cesse se passer dans ta vie psychique bien plus de choses qu'il ne peut s'en r??v??ler ?? ta conscience. Tu te comportes comme un monarque absolu qui se contente des informations que lui donnent les hauts dignitaires de la cour et qui ne descend pas vers le peuple pour entendre sa voix. Rentre en toi-m??me profond??ment et apprends d'abord ?? te conna??tre, alors tu comprendras pourquoi tu vas tomber malade, et peut-??tre ??viteras-tu de le devenir. ??C'est de cette mani??re que la psychanalyse voudrait instruire le moi. Mais les deux clart??s qu'elle nous apporte : savoir que la vie instinctive de la sexualit?? ne saurait ??tre compl??tement dompt??e en nous et que les processus psychiques sont en eux-m??mes inconscients, et ne deviennent accessibles et subordonn??s au moi que par une perception incompl??te et incertaine, ??quivalent ?? affirmer que le moi n'est pas ma??tre dans sa propre maison. Freud, Essais de psychanalyse appliqu??e.")
    compare("Aragorn ??tait le plus grand de la Compagnie, mais Boromir, de taille l??g??rement moins ??lev??e, ??tait de carrure plus large et plus lourde. Il passa devant et Aragorn le suivit. Lentement, ils se mirent en marche, et bient??t ils peinaient ferme. La neige leur arrivait par endroits ?? la poitrine, et Boromir paraissait plut??t nager ou creuser avec ses grands bras que marcher.\n\nApr??s les avoir observ??s un moment, Legolas se tourna vers les autres, un sourire aux l??vres :\n\n??? Les plus forts doivent chercher un chemin, disiez-vous ? Mais moi je dis : qu???un laboureur laboure, mais choisissez plut??t une loutre pour nager et pour courir l??g??rement sur l???herbe et les feuilles, ou m??me la neige ??? un Elfe.\n\nSur quoi, il s?????lan??a lestement, et Frodon remarqua pour la premi??re fois, bien qu???il le s??t depuis longtemps, que l???Elfe n???avait pas de bottes, mais qu???il portait seulement des chaussures l??g??res, comme il faisait toujours ; et ses pieds laissaient ?? peine de traces dans la neige.\n\n??? Adieu ! dit-il ?? Gandalf. Je vais chercher le soleil.\n\nAlors, avec la rapidit?? d???un coureur sur du sable ferme, il partit en fl??che, et, ayant vite rattrap?? les hommes qui peinaient, il les d??passa avec un signe de la main, il poursuivit son chemin ?? toute vitesse et disparut derri??re l???ar??te de rocher.\n\nLes autres attendirent, serr??s les uns contre les autres et observant jusqu???au moment o?? Boromir et Aragorn ne furent plus que des points noirs dans la blancheur. Enfin, eux aussi disparurent. Le temps se tra??na. Les nuages s???abaiss??rent, et quelques flocons de neige recommenc??rent ?? tourbillonner.\n\nUne heure peut-??tre passa, quoique cela par??t bien plus long, et enfin ils virent revenir Legolas. En m??me temps Boromir et Aragorn reparurent au tournant, loin derri??re lui, et ils s???avanc??rent p??niblement sur la pente.\n\n??? Apr??s tout, cria Legolas tandis qu???il accourait, je n???ai pas apport?? la Soleil. Elle se prom??ne dans les champs bleus du Sud et une l??g??re couronne de neige sur cette petite butte de Rubicorne ne la trouble nullement. Mais j???ai rapport?? un rayon de bonne esp??rance pour ceux qui sont condamn??s ?? aller ?? pied. Il y a la plus grande de toutes les cong??res juste au-del?? du tournant, et l??, nos Hommes forts ont ??t?? presque enterr??s. Ils d??sesp??raient jusqu???au moment o?? je suis revenu leur dire que la cong??re ??tait ?? peine plus ??paisse qu???un mur. De l???autre c??t??, la neige diminue tout d???un coup pour devenir un peu plus bas une simple courtepointe blanche pour rafra??chir les pieds des Hobbits.")
    compare("http://La-Philosophie.com Le cas d'une dissertation redig??e et corrig??e La dissertation en philosophie est un exercice difficile car elle suppose la ma??trise d'une m??thode et d'une structure d??termin??e. Nous vous donnons donc un exemple de dissertation redig??e et corrig??e par un professeur, tant d'un point de vue m??thodologique (forme) qu'??ditorial (fond). Nous avons volontairement choisi un sujet de dissertation tr??s classique en terminale philo : \"La libert?? est-elle une illusion ?\" (fr??quent pour les terminales litt??raires) La libert?? est-elle une illusion ? Travail pr??paratoire A) L???analyse des termes du sujet : 1) La libert?? : Il s???agit de toujours partir de la conception spontan??e, imm??diate que l???on se fait de la libert??, celle de l??? ?? homme de la rue ?? qu???aurait pu interroger Socrate. Ainsi, la libert??, c???est ?? faire ce que l???on veut ??, elle correspond, semble-t-il ?? la toute-puissance de la volont?? de chacun. Spontan??ment, tout individu se sent libre d??s lors qu???il peut accomplir tous ses d??sirs, toutes ses envies. Or l???exp??rience ordinaire de la vie montre aussi, paradoxalement, l?????tre humain soumis ?? de nombreuses contraintes ?? la fois externes (physiques, sociales, politiques) et internes (instincts, habitudes, passions) qui p??sent sur sa libert?? et qu???il lui est difficile voire impossible de surmonter totalement de sa propre initiative. D??s lors, le sentiment de libert?? ne serait-il qu???illusoire ? 2) l???illusion : Il s???agit de saisir l???importance de ce terme ?? distinguer de l???erreur. L???illusion proc??de certes de l???erreur en ce qu???elle trompe l???individu, mais elle proc??de ??galement de la mystification. Qu???est-ce ?? dire ? Tout individu est responsable de ses erreurs et dispose du pouvoir de les corriger. En revanche, dans l???illusion, qui peut ??tre ?? la fois individuelle et collective, nous serions victimes d???une puissance trompeuse impossible ?? vaincre. La question qui s???impose est donc la suivante : Quel type de d??sir proprement humain se trouve ?? la racine d???une illusion ? Ou bien quel besoin l???homme cherche-t-il ?? satisfaire dans la p??rennit?? d???une illusion ? B) Rep??rer les notions du programme en jeu dans le sujet : la libert??, la conscience et l???inconscient, le d??sir. C) Probl??matiser le sujet : Si tout individu ??prouve un sentiment imm??diat de libert??, cette conviction renvoie-t-elle ?? une croyance illusoire ou ?? une v??ritable connaissance de soi ? L???objectif consistera donc ?? faire la part de ce qui rel??ve d???une libert?? r??elle, rep??rable, de ce qui rel??ve d???un d??sir infond?? de libert??, dans un souci de lucidit?? et de v??rit??. http://La-Philosophie.com D) Mobiliser des r??f??rences utilisables : - Platon, dans le Gorgias, d??nonce la confusion commune entre la libert?? du sage et la r??alisation impulsive de tous ses d??sirs. - Descartes, dans La M??ditation quatri??me, donne une d??finition du libre arbitre qui apparente l???homme ?? Dieu. - Spinoza, dans L???Ethique, montre que la conscience d???exister n???implique pas n??cessairement la libert?? humaine. E) Elaboration du plan : elle doit ob??ir ?? la r??gle du ?? plus proche au plus lointain ??, c???est-??-dire aller de l???explicite ?? l???implicite, du plus ??vident au moins ??vident. Exemple de plan possible : I) La libert?? est un sentiment imm??diat : la th??se du libre arbitre II) La critique d??terministe du libre arbitre III) La libert?? est ?? conqu??rir : de la lib??ration ?? la qu??te d???autonomie Introduction ?? la dissertation 1) Amorce : Il nous faut partir de ce constat de d??part que le sentiment commun et imm??diat ??prouv?? par tout homme est de se sentir libre : en effet, chaque homme peut faire l???exp??rience, du moins int??rieure, d???une libert?? de penser et d???agir, ind??pendamment de toute contrainte ext??rieure. Cette conviction int??rieure est donc profond??ment ancr??e en chacun de nous. 2) Annonce du sujet et probl??matisation : Cependant, la libert?? ne serait-elle pas une illusion ? Ou pour le dire autrement, le fait de se sentir libre n???est-il pas susceptible de ne renvoyer qu????? une croyance illusoire ? Le sentiment imm??diat de notre libert?? est-il vrai, c???est-??-dire renvoie-t-il ?? une v??ritable connaissance de soi-m??me ? 3) Annonce du plan d?????tude : elle doit ??tre suffisamment explicite sans en dire trop, sans ??tre trop ?? lourde ?? : Nous tenterons, tout d???abord, d?????valuer la pertinence et les limites du sentiment spontan?? de libert??, commun ?? tous les hommes. Puis nous t??cherons de montrer que cette exp??rience imm??diate du libre arbitre est susceptible de camoufler ?? l???homme une m??connaissance de lui-m??me. Enfin, une nouvelle t??che se dressera face ?? nous : la n??cessit?? de reconstruire une nouvelle approche de la libert?? humaine, si tant est qu???elle soit possible. D??veloppement de la dissertation : 1??re partie http://La-Philosophie.com I) Le sentiment imm??diat de notre libert?? : la th??orie du libre arbitre a) Tout homme se juge spontan??ment libre Dans le langage courant, la libert?? renvoie au pouvoir que poss??de tout homme de n???ob??ir qu????? lui-m??me, qu????? sa propre volont??, et d???agir uniquement en fonction de ses d??sirs, ind??pendamment de toute contrainte ou de toute pression ext??rieure. Tout homme se sent donc spontan??ment libre, tout simplement parce qu???il se croit capable de faire des choix de petite ou de grande importance, de prendre des d??cisions, de petite ou de grande ampleur. Autrement dit, tout homme, lorsqu???il porte un regard r??flexif sur lui-m??me, se juge spontan??ment libre, c???est-??-dire en mesure d???agir simplement en fonction de sa volont??. La plupart des philosophes qui se sont prononc??s en faveur de la libert?? humaine, en faveur de l???existence du libre arbitre, ont accord?? une grande valeur ?? l???exp??rience intime, imm??diate que nous aurions, selon eux, de notre libert?? : ?? La libert?? de notre volont??, ??crit Descartes (Principes de la Philosophie, I, art.39), se conna??t sans preuve par la seule exp??rience que nous en avons ??. Transition : Faire le point et formuler une ou plusieurs questions permettant de poursuivre la r??flexion : La libert?? correspondrait donc ?? un sentiment int??rieur, ?? une exp??rience imm??diate en chaque homme. Or peut-on se contenter de cette exp??rience imm??diate ou pour reprendre la formulation de Bergson, de cette ?? donn??e imm??diate de la conscience ?? ? Autrement dit, peut-on se contenter du sentiment de notre libert?? pour en d??duire son existence certaine ? Est-il donc possible de faire une exp??rience de notre libert?? qui puisse justifier ce sentiment ? b) Peut-on prouver l???existence du libre arbitre ? 1) Premi??re tentative de preuve : l???exp??rience de l?????ne de Buridan et la mise ?? jour de la ?? libert?? d???indiff??rence ?? Jean Buridan, philosophe fran??ais du quatorzi??me si??cle, aurait, selon la l??gende, con??u une exp??rience imaginaire afin de prouver l???existence du libre arbitre : la situation serait celle d???un animal, en l???occurrence un ??ne, ayant ??galement faim et soif, et qui, plac?? ?? ??gale distance d???une botte de foin et d???un seau d???eau, h??site, se montre incapable de choisir, et finalement se laisse mourir. Ce ?? protocole exp??rimental m??taphysique ?? aurait donc pour objectif de prouver l???existence de la ?? libert?? d???indiff??rence ?? proprement humaine. En effet, nous avons tous d??j?? v??cu une situation o?? les mobiles ou motifs en faveur d???un acte ou d???un autre ??taient si ??quivalents, ou aussi contraignants l???un que l???autre, que nous nous sommes retrouv??s incapables de faire un choix. En effet, que se passe-t-il lorsqu???un individu se retrouve face ?? deux possibilit??s aussi ??quivalentes l???une que l???autre, lorsque rien ne puisse permettre de d??terminer son choix ? Or ce qui permet ?? l???homme d?????chapper ?? la situation absurde de l?????ne mourant de faim et de soif entre une botte de http://La-Philosophie.com foin et un seau d???eau, c???est qu???il dispose de cette libert?? d???indiff??rence, c???est-??-dire de cette libert?? par laquelle notre volont?? a le pouvoir de choisir spontan??ment et de sa propre initiative. Cette situation d???indiff??rence du choix prouve donc que l???homme est dot?? d???un libre arbitre, c???est??-dire d???une capacit?? de choisir pouvant ??chapper ?? tout d??terminisme. Pour Descartes, cette libert?? d???indiff??rence, bien que consid??r??e comme ?? le plus bas degr?? de la libert?? ??, t??moigne en m??me temps d???un pur libre arbitre qui apparente l???homme ?? Dieu (M??ditation quatri??me). 2) Seconde tentative de preuve du libre arbitre : le crime de Lafcadio dans Les Caves du Vatican d???Andr?? Gide Andr?? Gide, dans Les Caves du Vatican, cherche ?? illustrer la possibilit?? pour un ??tre humain de r??aliser un acte gratuit, c???est-??-dire un acte accompli sans raison, par le seul effet de sa libert??. Dans le roman, le ?? h??ro ?? Lafcadio se rend ?? Rome par le train et se retrouve seul dans la nuit, ne partageant son compartiment qu???avec un vieux monsieur. Lafcadio se prend alors d???une id??e folle : ?? L?? sous ma main, la poign??e. Il suffirait de la tirer et de le pousser en avant. On n???entendrait m??me pas un cri dans la nuit. Qui le verrait???Un crime immotiv??, quel embarras pour la police ??. Lafcadio se dit en effet, et ?? juste titre, que s???il n???a pas de mobiles pour r??aliser ce crime, il n???a donc pas de motivations. Le lien entre l???acteur et l???acte commis est inexistant. Lafcadio prend d???ailleurs un soin tout particulier ?? renforcer la gratuit?? de son crime : il remet tout au hasard et se met ?? compter pour soumettre sa d??cision de passer ?? l???acte ou de ne pas passer ?? l???acte ?? l???apparition d???un feu dans la nuit. Or le hasard, c???est pr??cis??ment ce qui est fortuit, c???est-??-dire d??pourvu de toute intention consciente, donc de motivation intrins??que??? Et le crime a lieu. 3) Peut-on dire que l???acte de Lafcadio est un acte gratuit ? Le m??rite du roman d???Andr?? Gide est d???aborder la question suivante : Un acte gratuit est-il possible ? Or deux critiques permettent d?????tre avanc??es pour remettre en cause cette possibilit?? : La premi??re critique consistera ?? remarquer que Lafcadio fait reposer son passage ?? l???acte sur des signes ext??rieurs, en l???occurrence l???apparition ou la non apparition d???un feu dans la campagne. Son acte serait donc d??termin?? par une ext??riorit??. La seconde critique consistera ?? remarquer que l???absence de motivations dans l???acte de Lafcadio est tout sauf ??vidente : l???une de ses premi??res motivations ne serait-elle pas le d??sir m??me de se prouver ?? lui-m??me sa libert?? ? Si bien qu???il est tout-?? fait envisageable de soup??onner Lafcadio de prendre pour une absence de motifs ce qui ne serait au fond qu???une ignorance profonde des motifs de son acte. L??? ?? acte gratuit ?? est donc une notion philosophiquement probl??matique : la volont?? de prouver sa libert?? par un acte suppos?? sans mobile constitue, par elle-m??me, un mobile. Transition : Une nouvelle question se pose d??s lors : le sentiment de libert?? ou la volont?? de r??aliser un acte non d??termin?? ne seraient-ils pas qu???une croyance ? Ne semble-t-il pas que ce ne http://La-Philosophie.com soit que de fa??on illusoire et superficielle que je fasse l??? ?? exp??rience ?? de ma libert??, par ignorance des d??terminations qui sont pourtant en jeu ? D??veloppement de la dissertation : 2??me partie II) La critique d??terministe du libre arbitre a) L???illusion anthropocentrique du libre arbitre : ?? L???homme n???est pas un empire dans un empire ?? (Spinoza) Le projet philosophique de B.Spinoza, dans le sillage des travaux scientifiques de Laplace, est de d??noncer les illusions du libre arbitre. C???est ainsi que dans la troisi??me partie de l???Ethique, dans la section intitul??e De l???origine et de la nature des affections, Spinoza rejette totalement l???id??e selon laquelle l???homme occuperait une place privil??gi??e au sein de la nature. Spinoza critique notamment Descartes qui con??oit l???homme comme ?? un empire dans un empire ??, ainsi que tous les philosophes qui croient que ?? l???homme trouble l???ordre de la Nature plut??t qu???il ne le suit, qu???il a sur ses propres actions un pouvoir absolu et ne tire que de lui-m??me sa d??termination ??. Or l???objectif de Spinoza est bel et bien de montrer que l???homme suit les lois communes de la Nature, comme toutes les choses de ce monde. b) L???illusion humaine de la libert?? C???est dans sa lettre ?? Schuller, extraite de sa Correspondance, que Spinoza d??nonce l???illusion du libre arbitre. Il d??fend ainsi une position philosophique d??terministe suivant laquelle tous les ??v??nements sont absolument n??cessaires et le sentiment que nous avons d?????tre libres ne serait qu???une illusion naturelle : ?? Telle est cette libert?? humaine que tous les hommes se vantent d???avoir et qui consiste en cela seul que les hommes sont conscients de leurs d??sirs et ignorants des causes qui les d??terminent ??. Et Spinoza d???ajouter un peu plus loin : ?? Et comme ce pr??jug?? est inn?? en tous les hommes, ils ne s???en lib??rent pas facilement ??. Cette illusion naturelle de l???homme a donc deux causes d???apr??s Spinoza qui justifient que l???homme s???illusionne et qu???il ne fasse pas seulement erreur. Premi??rement, la source de l???illusion humaine du libre arbitre est l???ignorance des causes qui nous poussent ?? agir. Or ?? prendre les choses rigoureusement, l???homme est tout aussi d??termin?? ?? se mouvoir sous l???influence de causes externes qu???une pierre qui re??oit une impulsion. Les hommes se croient libres alors qu???ils sont contraints ou d??termin??s par leur nature. Deuxi??mement, Spinoza pr??cise bien que les hommes ?? se vantent ?? d?????tre libre car le d??sir d?????tre libre, m??me illusoire, est beaucoup plus valorisant pour l???orgueil humain que l???id??e d?????tre totalement d??termin??. http://La-Philosophie.com c) La libert?? d??signe alors la n??cessit?? bien comprise C???est ainsi que Spinoza ne fait pas consister la libert??, dans la lettre ?? Schuller, dans un libre d??cret mais dans une libre n??cessit?? ou dans la n??cessit?? bien comprise : ?? j???appelle libre, quant ?? moi, une chose qui est et agit par la seule n??cessit?? de sa nature ??. Tout comme les comportements des animaux sont d??termin??s par l???instinct, leur environnement ou des d??terminations biologiques, les actes et les pens??es des hommes le sont eux-m??mes par de multiples facteurs ?? la fois internes et externes dont on ignore le plus souvent l???existence et la puissance : facteurs d???origine physiologiques, psychologiques, sociales, etc. D??s lors, l???un des apports essentiels de la critique spinoziste du libre arbitre est de montrer que la croyance en l???existence du libre arbitre est la source d???ali??nation de l???homme. En effet, selon Spinoza, non seulement l???homme est d??termin?? mais cette illusion naturelle du libre arbitre nous d??terminent ?? ne pas savoir que nous sommes d??termin??s, et ainsi ?? l?????tre d???autant plus s??rement. Or il n???y a pas pire esclave que celui qui se croit libre. Transition : Il nous faut donc tirer les enseignements de la critique spinoziste du libre arbitre et reconna??tre que l???id??e d???une libert?? spontan??e ou d???un sentiment imm??diat de libert?? n???est plus tenable. Est-il d??s lors possible de reconstruire une approche de la libert?? qui soit accessible ?? l???homme ? D??veloppement de la dissertation ; 3??me et derni??re partie III) La libert?? est ?? conqu??rir : de la lib??ration ?? la qu??te d???autonomie a) ??tre libre, c???est apprendre ?? se lib??rer des passions Platon, dans le Gorgias, pose la question suivante : est-ce la vie de l???homme aux d??sirs insatiables ou celle guid??e par la raison qui est la meilleure ? Dans ce dialogue qui met aux prises Socrate et Callicl??s, ce dernier d??fend le droit au d??sir, comme un droit ?? ??tre puissant, autrement dit ?? ??tre capable de mettre les forces de son ??nergie et de son intelligence au service des passions, pour leur donner la plus grande ampleur possible. C???est ainsi que Callicl??s pr??f??re les ?? tonneaux qui fuient ?? puisque ?? ce qui fait l???agr??ment de la vie, c???est de verser le plus possible ??. En revanche, Socrate choisit la vie ordonn??e, celle o?? les tonneaux du sage ?? seraient en bon ??tat ??. Platon cherche ainsi ?? montrer, dans ce dialogue, l???illusion dans laquelle se trouvent les hommes comme Callicl??s, qui croient qu?????tre libre consiste ?? faire ce que l???on veut, c???est-??-dire ?? r??aliser tous ses d??sirs. Or une telle vie, guid??e par des d??sirs multiples, polymorphes et surtout infinis, m??ne n??cessairement au tourment et au malheur. En effet, le risque pour un homme comme http://La-Philosophie.com Callicl??s d??cidant de mener une vie intemp??rante et d??sordonn??e est de devenir l???esclave de ses propres passions et d??sirs. A cette vie d??sordonn??e, Platon oppose une vie guid??e par la raison, incarn??e par la sagesse socratique. Socrate incarne, en effet, le sage qui sait distinguer entre les d??sirs ?? poursuivre ou ?? ne pas poursuivre, qui sait se gouverner lui-m??me et qui est en mesure d???acc??der ?? une v??ritable autonomie de la volont??. b) ??tre libre, c???est ??tre responsable de ses actes Par cons??quent, l???entr??e dans la libert?? authentique, par opposition avec la libert?? illusoire des d??sirs infinis, c???est l???entr??e dans une v??ritable autonomie et c???est pouvoir devenir responsable de ses actes et pouvoir en r??pondre. L???enjeu de l???entr??e dans la libert?? authentique est donc celui du rapport ?? soi-m??me et ?? autrui. La libert?? entre alors dans le champ de la r??flexion morale, sociale et politique. C???est ainsi qu???au sens moral et juridique, ??tre libre, c???est pouvoir ??tre reconnu autonome et responsable de ses actes, de ses choix, ?? la fois devant soi-m??me et devant la soci??t?? ?? laquelle on appartient. En cons??quence, si la libert?? est illusoire ou inaccessible, il semble que c???en soit fini de la responsabilit?? morale et juridique de tout individu, et par l?? m??me de la justice. Le fait que nous nous sentions, ?? tort ou ?? raison libre, exige donc que l???on agisse comme si on ??tait effectivement libre. c) La libert?? comme condition de l???acte ??thique C???est ainsi que dans la premi??re note de la pr??face ?? la Critique de la raison pratique, Kant affirme que la libert?? est la condition de possibilit?? et l???essence (la ratio essendi) de la vie morale de l???homme, comme la vie morale de l???homme est ce par quoi l???homme conna??t la r??alit?? de sa libert?? (elle en est la ratio cognoscendi). Et Kant ajoute pour pr??ciser : ?? (???) si la loi morale n?????tait pas d???abord clairement con??ue dans notre raison, nous ne nous croirions jamais autoris??s ?? admettre une chose telle que la libert?? (???). En revanche, s???il n???y avait pas de libert??, la loi morale ne saurait nullement ??tre rencontr??e en nous ??. Ainsi, pour Kant, pour que l???homme soit moral, il faut qu???il soit libre, car s???il ??tait forc?? par une nature intelligible ?? la bont??, ?? la justice et ?? l???altruisme, il ne serait qu???un automate spirituel et s???il ??tait forc?? par sa nature sensible ?? l?????go??sme, il ne serait qu???un m??canisme mat??riel. Conclusion de de notre exemple sur la dissertation philosophique 1) Faire le bilan de la d??marche poursuivie dans le devoir : La libert?? humaine est-elle donc possible ? Nous avons pu comprendre, tout au long de notre travail, la difficult?? qui existe ?? http://La-Philosophie.com pouvoir saisir une v??ritable ?? exp??rience ?? de la libert?? et, par cons??quent, la difficult?? ?? en prouver v??ritablement l???existence. 2) R??pondre ?? la question initiale : La libert?? est-elle une illusion ? Notre travail a, en tout cas, cherch?? ?? d??montrer que si la croyance en une libert?? imm??diate ??tait illusoire, voire na??ve, la critique spinoziste nous a permis d???acc??der ?? une approche de la libert?? qui puisse permettre d???en pr??server l???espoir : en effet, si l???homme n???est pas libre, il lui est, en revanche, donn?? d???entrer dans un processus, dans une conqu??te assimilable ?? une lib??ration par l???usage de la raison et par son entr??e dans la morale et la vie sociale. 3) Si possible, proposer une ouverture ?? une nouvelle r??flexion : Comment penser les cons??quences d???une authentique lib??ration de l???homme dans ses interactions morales, sociales et politiques ? Vincent Boyer, professeur de philosophie ?? Paris. Pour aller plus loin sur le bac philosophie : M??thode de la dissertation philosophique Le Commentaire de Philosophie Aide ?? la dissertation de Philosophie")
  }
}
