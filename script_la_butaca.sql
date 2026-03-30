-- 1. Reinicio total de la base de datos
DROP DATABASE IF EXISTS la_butaca_db;
CREATE DATABASE la_butaca_db;
USE la_butaca_db;

-- 2. Creación de tablas
CREATE TABLE categorias (
    id_categoria INT PRIMARY KEY, 
    nombre VARCHAR(50)
);

CREATE TABLE contenido (
    id_contenido INT AUTO_INCREMENT PRIMARY KEY,
    titulo VARCHAR(100),
    synopsis TEXT,
    id_categoria INT,
    FOREIGN KEY (id_categoria) REFERENCES categorias(id_categoria)
);

INSERT INTO categorias VALUES (1, 'Películas'), (2, 'Series');

-- 3. Catálogo final de 32 elementos (Cuadrícula perfecta de 4x8)
INSERT INTO contenido (titulo, synopsis, id_categoria) VALUES 
('arcane', 'Serie de animación de LoL.', 2),
('avatar', 'Aventura en Pandora.', 1),
('bajolamismaestrella', 'Drama romántico adolescente.', 1),
('bbad', 'Breaking Bad: Química y crimen.', 2),
('blackmirror', 'Historias tecnológicas oscuras.', 2),
('bladerunner', 'Cazadores de replicantes.', 1),
('dune', 'Épica en el desierto de Arrakis.', 1),
('elclubdelalucha', 'Jabón y peleas clandestinas.', 1),
('et', 'E.T. El extraterrestre.', 1),
('forrest', 'La vida de Forrest Gump.', 1),
('gladiator', 'Venganza en la antigua Roma.', 1),
('indianajones', 'El arqueólogo más famoso.', 1),
('interestelar', 'Viaje espacial y agujeros de gusano.', 1),
('insideout', 'Las emociones de una niña cobran vida en su mente.', 1), -- LA NÚMERO 32
('juegodelcalamar', 'Juegos infantiles mortales.', 2),
('laballena', 'Redención y obesidad severa.', 1),
('lasociedaddelanieve', 'Supervivencia en los Andes.', 1),
('maverick', 'Top Gun: Vuelo de alto riesgo.', 1),
('MaxMax', 'Persecuciones post-apocalípticas.', 1),
('ministeriodeltiempo', 'Patrullas que viajan por la historia.', 2),
('narcos', 'Carteles en Colombia.', 2),
('onepiece', 'Aventura pirata de Luffy.', 2),
('parásitos', 'Una familia se infiltra en otra.', 1),
('pulpfiction', 'Historias cruzadas de Tarantino.', 1),
('spiderman', 'El hombre araña en Nueva York.', 1),
('succesion', 'Lucha de poder familiar.', 2),
('thelast', 'Mundo post-apocalíptico infectado.', 2),
('toystory', 'La vida secreta de los juguetes.', 1),
('unlugartranquilo', 'Supervivencia en silencio absoluto.', 1),
('vikingos', 'Sagas de guerreros nórdicos.', 2),
('wallie', 'Robot solitario limpia la Tierra.', 1),
('zootropolis', 'Una coneja policía en la gran ciudad.', 1);

-- Verificación final
SELECT COUNT(*) AS 'Total Filas' FROM contenido;