CREATE TABLE category (
                          category_id serial PRIMARY KEY,
                          name VARCHAR(255) NOT NULL
);
CREATE TABLE product (
                         product_id serial PRIMARY KEY,
                         name VARCHAR(255) NOT NULL,
                         producer VARCHAR(255) NOT NULL,
                         description TEXT,
                         price DECIMAL(10, 2) NOT NULL,
                         category_id INT NOT NULL,
                         FOREIGN KEY (category_id) REFERENCES category(category_id)
);

CREATE TABLE client (
                        client_id serial PRIMARY KEY,
                        nickname VARCHAR(255) NOT NULL,
                        password VARCHAR(255) NOT NULL,
                        email VARCHAR(255) NOT NULL
);

INSERT INTO category (name) VALUES ('Электрогитары'), ('Бас-гитары'), ('Акустические гитары');
INSERT INTO product (name, producer, description, price, category_id) VALUES ('GSR200B-WNF', 'Ibanez', 'Электрическая бас-гитара, модель орехового цвета, 22 лада.' ||
                                                                                                       ' Дека махагони, гриф клен, накладка новозеландская сосна.' ||
                                                                                                       ' Звукосниматели PJ, активный тембр-блок, эквалайзер.', 29400, 2),
                                                                             ('GSR200B-WNF', 'Ibanez', 'Электрическая бас-гитара, модель орехового цвета, 22 лада.' ||
                                                                                                       ' Дека махагони, гриф клен, накладка новозеландская сосна.' ||
                                                                                                       ' Звукосниматели PJ, активный тембр-блок, эквалайзер.', 29400, 2),
                                                                             ('GSR200B-WNF', 'Ibanez', 'Электрическая бас-гитара, модель орехового цвета, 22 лада.' ||
                                                                                                       ' Дека махагони, гриф клен, накладка новозеландская сосна.' ||
                                                                                                       ' Звукосниматели PJ, активный тембр-блок, эквалайзер.', 29400, 2),
                                                                             ('SQUIER Affinity 2021 Stratocaster HH LRL Olympic White', 'Fender', 'Электрогитара, цвет белый,' ||
                                                                                                                                                  ' корпус - тополь, гриф - клён,' ||
                                                                                                                                                  ' накладка грифа - индийский лавр, порожек - синтетическая кость,' ||
                                                                                                                                                  ' профиль грифа - C-shape, лады - 21 Medium Jumbo, мензура 25,5` (648 мм),' ||
                                                                                                                                                  ' звукосниматели HH - Fender Designed Affinity Pickups (керамические магниты),' ||
                                                                                                                                                  ' регуляторы - 1 громкость, 1 тон, 3-х позиционный переключатель, бридж - 2-Point' ||
                                                                                                                                                  ' Synchronized Tremolo, колки - литые, фурнитура - хром', 51850, 1),
                                                                             ('SQUIER Affinity 2021 Stratocaster HH LRL Olympic White', 'Fender', 'Электрогитара, цвет белый,' ||
                                                                                                                                                  ' корпус - тополь, гриф - клён,' ||
                                                                                                                                                  ' накладка грифа - индийский лавр, порожек - синтетическая кость,' ||
                                                                                                                                                  ' профиль грифа - C-shape, лады - 21 Medium Jumbo, мензура 25,5` (648 мм),' ||
                                                                                                                                                  ' звукосниматели HH - Fender Designed Affinity Pickups (керамические магниты),' ||
                                                                                                                                                  ' регуляторы - 1 громкость, 1 тон, 3-х позиционный переключатель, бридж - 2-Point' ||
                                                                                                                                                  ' Synchronized Tremolo, колки - литые, фурнитура - хром', 51850, 1),
                                                                             ('SQUIER Affinity 2021 Stratocaster HH LRL Olympic White', 'Fender', 'Электрогитара, цвет белый,' ||
                                                                                                                                                  ' корпус - тополь, гриф - клён,' ||
                                                                                                                                                  ' накладка грифа - индийский лавр, порожек - синтетическая кость,' ||
                                                                                                                                                  ' профиль грифа - C-shape, лады - 21 Medium Jumbo, мензура 25,5` (648 мм),' ||
                                                                                                                                                  ' звукосниматели HH - Fender Designed Affinity Pickups (керамические магниты),' ||
                                                                                                                                                  ' регуляторы - 1 громкость, 1 тон, 3-х позиционный переключатель, бридж - 2-Point' ||
                                                                                                                                                  ' Synchronized Tremolo, колки - литые, фурнитура - хром', 51850, 1),
                                                                             ('SQUIER Affinity 2021 Stratocaster HH LRL Olympic White', 'Fender', 'Электрогитара, цвет белый,' ||
                                                                                                                                                  ' корпус - тополь, гриф - клён,' ||
                                                                                                                                                  ' накладка грифа - индийский лавр, порожек - синтетическая кость,' ||
                                                                                                                                                  ' профиль грифа - C-shape, лады - 21 Medium Jumbo, мензура 25,5` (648 мм),' ||
                                                                                                                                                  ' звукосниматели HH - Fender Designed Affinity Pickups (керамические магниты),' ||
                                                                                                                                                  ' регуляторы - 1 громкость, 1 тон, 3-х позиционный переключатель, бридж - 2-Point' ||
                                                                                                                                                  ' Synchronized Tremolo, колки - литые, фурнитура - хром', 51850, 1),
                                                                             ('F310', 'Yamaha', 'Акустическая гитара, верхняя дека ель, нижняя дека и обечайка меранти,' ||
                                                                                                ' гриф нато, накладка на гриф палисандр, хромированные колки,' ||
                                                                                                ' цвет натуральный', 19990, 3),
                                                                             ('F310', 'Yamaha', 'Акустическая гитара, верхняя дека ель, нижняя дека и обечайка меранти,' ||
                                                                                                ' гриф нато, накладка на гриф палисандр, хромированные колки,' ||
                                                                                                ' цвет натуральный', 19990, 3),
                                                                             ('F310', 'Yamaha', 'Акустическая гитара, верхняя дека ель, нижняя дека и обечайка меранти,' ||
                                                                                                ' гриф нато, накладка на гриф палисандр, хромированные колки,' ||
                                                                                                ' цвет натуральный', 19990, 3);

CREATE TABLE chat_room (
    room_id bigserial PRIMARY KEY
);

CREATE TABLE chat_message (
                              message_id bigserial PRIMARY KEY,
                              sender_id integer NOT NULL,
                              content text NOT NULL,
                              timestamp timestamp NOT NULL,
                              room_id bigint NOT NULL,
                              FOREIGN KEY (room_id) REFERENCES chat_room(room_id)
);


CREATE TABLE chat_room_clients (
                                   room_id bigserial,
                                   client_id integer,
                                   PRIMARY KEY (room_id, client_id),
                                   FOREIGN KEY (room_id) REFERENCES chat_room(room_id),
                                   FOREIGN KEY (client_id) REFERENCES client(client_id)
);

CREATE TABLE token (
                                   token text,
                                   client_id integer,
                                   PRIMARY KEY (client_id),
                                   FOREIGN KEY (client_id) REFERENCES client(client_id)
);
