package pablogarcia.meetup.Managers.LocalRepositoryManager;

import java.util.ArrayList;

import pablogarcia.meetup.Model.Meet;

public class LocalRepository {

    ArrayList<Meet> meets;

    public static final ArrayList<Meet> createExampleMeets(){
        ArrayList<Meet> meets = new ArrayList<>();
        Meet meet1 = new Meet("Concierto la Pegatina",
                "Concierto de la pegatina, el segundo este año" ,
                "http://www.kebuena.com.mx/wp-content/uploads/2016/05/concierto.jpg",
                "13 de Mayo de 2019",
                "14 de Mayo de 2019");
        Meet meet2 = new Meet("Cena por mi cumple",
                "Cena en casa, con un buen vino!" ,
                "https://www.ecestaticos.com/imagestatic/clipping/84f/08d/84f08d950edd46b671252b62da6e2a39/10-cosas-que-nunca-debes-hacer-en-una-cena-reveladas-por-un-mayordomo-experto.jpg?mtime=1471358353",
                "13 de Mayo de 2019",
                "14 de Mayo de 2019");
        Meet meet3 = new Meet("Cena por mi cumple",
                "Cena en el Jose Maria" ,
                "Imagen Evento 1",
                "13 de Mayo de 2019",
                "14 de Mayo de 2019");
        Meet meet4 = new Meet("Cena por mi cumple",
                "Cena en el Jose Maria" ,
                "Imagen Evento 1",
                "13 de Mayo de 2019",
                "14 de Mayo de 2019");
        Meet meet5 = new Meet("Cena por mi cumple",
                "Cena en el Jose Maria" ,
                "Imagen Evento 1",
                "13 de Mayo de 2019",
                "14 de Mayo de 2019");
        Meet meet6 = new Meet("Cena por mi cumple",
                "Cena en el Jose Maria" ,
                "Imagen Evento 1",
                "13 de Mayo de 2019",
                "14 de Mayo de 2019");
        Meet meet7 = new Meet("Cena por mi cumple",
                "Cena en el Jose Maria" ,
                "Imagen Evento 1",
                "13 de Mayo de 2019",
                "14 de Mayo de 2019");
        Meet meet8 = new Meet("Cena por mi cumple",
                "Cena en el Jose Maria" ,
                "Imagen Evento 1",
                "13 de Mayo de 2019",
                "14 de Mayo de 2019");

        meets.add(meet1);
        meets.add(meet2);
        meets.add(meet3);
        meets.add(meet4);
        meets.add(meet5);
        meets.add(meet6);
        meets.add(meet7);
        meets.add(meet8);

        return meets;
    }

    public ArrayList<Meet> getMeets() {
        return meets;
    }
}
