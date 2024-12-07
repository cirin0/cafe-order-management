INSERT INTO dishes (available, category, description, image, name, price)
VALUES (true, 'APPETIZER', 'Свіжий салат ромен з соусом Цезар',
        'https://znatnakurka.ua/sites/default/files/2024-02/salat-cezar-klassicheskii-s-kuricei_1611309331_16_max.jpeg',
        'Салат Цезар', 120),
       (true, 'MAIN_COURSE', 'Класична піца з помідорами, моцарелою та базиліком',
        'https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcTxi26uWKZBojC8b711bYl0TP-RSH1x6JXrIg&s',
        'Піца Маргарита', 250),
       (true, 'DESSERT', 'Насичений і вологий шоколадний торт',
        'https://vatsak.com.ua/image/cache/catalog/products/Tortu/KARTONNA%20UPAKOVKA/Shokoladnij/Shoko-Gall1-562x429.jpg',
        'Шоколадний торт', 150),
       (true, 'DRINK', 'Освіжаючий лимонад з ноткою м’яти',
        'https://cdn.metro-online.com/-/media/Project/MCW/UA_Metro/Blog/2021/07_2021/Klasychnyy-lymonad/Metro_shop_45945933.jpg?h=440&iar=0&w=440&rev=96ae192668894717ab7e1025b4cd4503&hash=DC79FC4F80CD78702371A51B56C9E5A1',
        'Лимонад', 50),
       (true, 'MAIN_COURSE', 'Спагеті з кремовим соусом карбонара',
        'https://i.obozrevatel.com/food/recipemain/2019/1/1/1425992371pasta-karbonara.jpeg?size=636x424',
        'Спагеті Карбонара', 200),
       (true, 'DESSERT', 'Класичний італійський десерт з кавою та маскарпоне',
        'https://i.obozrevatel.com/food/recipemain/2019/1/29/tiramisutort.jpg?size=636x424', 'Тірамісу', 180),
       (false, 'DRINK', 'Класичний коктейль з ромом, м’ятою та лаймом',
        'https://smachno.ua/wp-content/uploads/2023/06/23/mojito.png', 'Мохіто', 80);

INSERT INTO customers (name, email, phone_number)
VALUES ('Тарас', 'taras@gmail.com,', '1231232412'),
       ('Олег', 'oleh@gmail.com,', '2131231067'),
       ('Ігор', 'ihor@gmail.com', '1231231231');

INSERT INTO orders (order_time, status, total_price, customer_id)
VALUES ('2024-12-08 00:00:03.033236', 'IN_PROGRESS', 520, 1),
       ('2024-12-08 00:00:14.399389', 'IN_PROGRESS', 370, 2);

INSERT INTO order_dishes (order_id, dish_id)
VALUES (1, 1),
       (1, 2),
       (1, 3),
       (2, 1),
       (2, 2);
