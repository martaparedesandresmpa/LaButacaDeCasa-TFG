package controlador;

import java.util.ArrayList;
import java.util.List;
import modelo.contenido;

public class GestorCatalogo {

    public List<contenido> obtenerPeliculas() {
        List<contenido> lista = new ArrayList<>();

        lista.add(new contenido("Arcane", "Serie", "Animación / Acción", 2021,
                "En medio del conflicto entre Piltover y Zaun, dos hermanas quedan separadas por la violencia, la ambición y una tecnología capaz de cambiar su mundo para siempre.",
                "", "arcane.jpg"));

        lista.add(new contenido("Avatar", "Película", "Ciencia ficción / Aventura", 2009,
                "Jake Sully viaja a Pandora y se introduce en la cultura na’vi mientras los humanos intentan explotar los recursos del planeta.",
                "", "avatar.jpg"));

        lista.add(new contenido("Bajo la misma estrella", "Película", "Drama / Romance", 2014,
                "Hazel, una adolescente con cáncer, conoce a Gus en un grupo de apoyo y ambos viven una historia de amor marcada por la enfermedad y el deseo de encontrar sentido a su vida.",
                "", "bajolamismaestrella.jpg"));

        lista.add(new contenido("Breaking Bad", "Serie", "Drama / Crimen", 2008,
                "Walter White, un profesor de química con problemas económicos y cáncer, empieza a fabricar metanfetamina junto a un antiguo alumno para asegurar el futuro de su familia.",
                "", "bbad.jpg"));

        lista.add(new contenido("Black Mirror", "Serie", "Ciencia ficción / Drama", 2011,
                "Serie antológica que muestra el lado más inquietante de la tecnología y cómo puede transformar la vida humana con consecuencias imprevisibles.",
                "", "blackmirror.jpg"));

        lista.add(new contenido("Blade Runner 2049", "Película", "Ciencia ficción / Thriller", 2017,
                "En un futuro dominado por replicantes, el agente K descubre un secreto que puede alterar el equilibrio entre humanos y seres artificiales.",
                "", "bladerunner.jpg"));

        lista.add(new contenido("Dune", "Película", "Ciencia ficción / Aventura", 2021,
                "Paul Atreides llega al planeta Arrakis con su familia y queda atrapado en una lucha política y militar por el control de la especia, el recurso más valioso de la galaxia.",
                "", "dune.jpg"));

        lista.add(new contenido("El club de la lucha", "Película", "Drama / Thriller", 1999,
                "Un hombre aburrido de su vida conoce a Tyler Durden, y juntos crean un club clandestino que acaba convirtiéndose en un movimiento cada vez más peligroso.",
                "", "elclubdelalucha.jpg"));

        lista.add(new contenido("E.T.", "Película", "Ciencia ficción / Familiar", 1982,
                "Un niño llamado Elliott encuentra a un extraterrestre perdido y trata de ayudarlo a volver a casa mientras nace entre ellos una amistad muy especial.",
                "", "et.jpg"));

        lista.add(new contenido("Forrest Gump", "Película", "Drama / Romance", 1994,
                "Forrest Gump atraviesa varias décadas de la historia de Estados Unidos con ingenuidad, bondad y un amor constante por Jenny.",
                "", "forrest.jpg"));

        lista.add(new contenido("Gladiator", "Película", "Acción / Drama", 2000,
                "Tras la traición de Cómodo, el general Máximo pierde todo y termina convertido en gladiador, decidido a vengarse.",
                "", "gladiator.jpg"));

        lista.add(new contenido("Indiana Jones", "Película", "Aventura / Acción", 1981,
                "Indiana Jones debe encontrar el Arca de la Alianza antes de que los nazis se apoderen de ella y usen su supuesto poder.",
                "", "indianajones.jpg"));

        lista.add(new contenido("Inside Out", "Película", "Animación / Familiar", 2015,
                "Las emociones de Riley intentan guiarla cuando su vida cambia por completo tras mudarse de ciudad.",
                "", "insideout.jpg"));

        lista.add(new contenido("Interstellar", "Película", "Ciencia ficción / Drama", 2014,
                "En una Tierra al borde del colapso, un grupo de astronautas cruza un agujero de gusano para buscar un nuevo hogar para la humanidad.",
                "", "interestelar.jpg"));

        lista.add(new contenido("John Wick", "Película", "Acción / Thriller", 2014,
                "Un asesino retirado vuelve a la acción para vengarse de quienes le arrebataron lo último que le unía a su antigua vida.",
                "", "johnwick.jpg"));

        lista.add(new contenido("Juego del calamar", "Serie", "Thriller / Drama", 2021,
                "Personas con graves problemas económicos aceptan participar en juegos infantiles mortales para optar a un enorme premio.",
                "", "juegodelcalamar.jpg"));

        lista.add(new contenido("La ballena", "Película", "Drama", 2022,
                "Un profesor de inglés recluido y con obesidad severa intenta reencontrarse con su hija en busca de redención.",
                "", "laballena.jpg"));

        lista.add(new contenido("La sociedad de la nieve", "Película", "Drama / Supervivencia", 2023,
                "Tras estrellarse su avión en los Andes, un grupo de supervivientes debe afrontar condiciones extremas para seguir con vida.",
                "", "lasociedaddelanieve.jpg"));

        lista.add(new contenido("Matrix", "Película", "Ciencia ficción / Acción", 1999,
                "Neo descubre que su realidad es falsa y que quizá tenga un papel decisivo en la lucha contra las máquinas.",
                "", "matrix.jpg"));

        lista.add(new contenido("Top Gun: Maverick", "Película", "Acción / Drama", 2022,
                "Después de décadas como piloto, Maverick regresa para entrenar a una nueva generación en una misión especialmente arriesgada.",
                "", "maverick.jpg"));

        lista.add(new contenido("Mad Max: Fury Road", "Película", "Acción / Ciencia ficción", 2015,
                "Max se une a Furiosa y a un grupo de fugitivas en una persecución brutal a través del desierto contra el tirano Immortan Joe.",
                "", "MaxMax.jpg"));

        lista.add(new contenido("El ministerio del tiempo", "Serie", "Aventura / Ciencia ficción", 2015,
                "Una institución secreta española protege la historia usando puertas que permiten viajar a distintas épocas.",
                "", "ministeriodeltiempo.jpg"));

        lista.add(new contenido("Narcos", "Serie", "Crimen / Drama", 2015,
                "La serie sigue el ascenso de Pablo Escobar y la lucha de la DEA contra el narcotráfico en Colombia.",
                "", "narcos.jpg"));

        lista.add(new contenido("One Piece", "Serie", "Aventura / Fantasía", 2023,
                "Luffy y su tripulación emprenden una aventura por mar en busca de un gran tesoro y de la libertad que representa.",
                "", "onepiece.jpg"));

        lista.add(new contenido("Parásitos", "Película", "Drama / Thriller", 2019,
                "La familia Kim se introduce poco a poco en la vida de una familia rica, desencadenando una relación llena de engaño y tensión social.",
                "", "parásitos.jpg"));

        lista.add(new contenido("Pulp Fiction", "Película", "Crimen / Drama", 1994,
                "Varias historias de crimen, violencia y humor negro se entrelazan en Los Ángeles alrededor de asesinos, boxeadores y mafiosos.",
                "", "pulpfiction.jpg"));

        lista.add(new contenido("Spider-Man 2", "Película", "Superhéroes / Acción", 2004,
                "Peter Parker intenta mantener el equilibrio entre su vida personal y su papel como Spider-Man mientras surge una nueva amenaza.",
                "", "spiderman.jpg"));

        lista.add(new contenido("Star Wars", "Película", "Ciencia ficción / Aventura", 1977,
                "La princesa Leia, Luke Skywalker y Han Solo se enfrentan al Imperio Galáctico en una aventura para devolver la esperanza a la galaxia.",
                "", "starwars.jpg"));

        lista.add(new contenido("Stranger Things", "Serie", "Ciencia ficción / Terror", 2016,
                "La desaparición de un niño en Hawkins destapa experimentos secretos, fuerzas sobrenaturales y la llegada de una niña con poderes.",
                "", "stranger_things.jpg"));

        lista.add(new contenido("Succession", "Serie", "Drama", 2018,
                "La familia Roy libra una guerra interna por el control de su poderoso imperio mediático y por decidir quién sucederá al patriarca.",
                "", "succession.jpg"));

        lista.add(new contenido("The Last of Us", "Serie", "Drama / Ciencia ficción", 2023,
                "En un mundo arrasado por una infección, Joel debe escoltar a Ellie a través de Estados Unidos en un viaje de supervivencia.",
                "", "thelast.jpg"));

        lista.add(new contenido("Titanic", "Película", "Drama / Romance", 1997,
                "Jack y Rose se conocen a bordo del Titanic y viven una historia de amor en medio del viaje más famoso y trágico del cine.",
                "", "titanic.jpg"));

        lista.add(new contenido("Toy Story", "Película", "Animación / Familiar", 1995,
                "Woody ve peligrar su lugar favorito cuando llega Buzz Lightyear, aunque ambos acabarán necesitando ayudarse.",
                "", "toystory.jpg"));

        lista.add(new contenido("Un lugar tranquilo", "Película", "Terror / Thriller", 2018,
                "Una familia intenta sobrevivir en silencio absoluto para no ser cazada por criaturas que atacan guiadas por el sonido.",
                "", "unlugartranquilo.jpg"));

        lista.add(new contenido("Vikingos", "Serie", "Acción / Drama histórico", 2013,
                "Ragnar Lothbrok y su familia protagonizan una historia de conquistas, ambición y luchas por el poder en el mundo vikingo.",
                "", "vikingos.jpg"));

        lista.add(new contenido("WALL·E", "Película", "Animación / Ciencia ficción", 2008,
                "En una Tierra abandonada y cubierta de basura, el robot WALL·E encuentra una nueva misión cuando conoce a EVE.",
                "", "wallie.jpg"));

        lista.add(new contenido("Zootrópolis", "Película", "Animación / Aventura", 2016,
                "En una ciudad donde conviven animales de todo tipo, la coneja Judy Hopps intenta resolver un caso junto al astuto zorro Nick Wilde.",
                "", "zootropolis.jpg"));

        return lista;
    }
}