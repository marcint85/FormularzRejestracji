# FormularzRejestracji

Autor: Marcin Tarnowski

kontakt: marcin.tarnowski2@gmail.com

Pliki: 

1 ) index.sjp - strona wyświetlana uzytkownikowi. Zawiera formularz z polami do uzupełnienia oraz listę osob juz 
dodanych do bazy danych.

2 ) DBconnection.java - klasa wykorzystywana do nawiazania polaczenia z baza danych. W wypadku zmiany adresu/konta/hasla bazy danych tutaj nalezy wprowadzic odpowiednie dane by nawiazac polaczenie. Driver wykorzystywany do polaczenia sie z baza danych: com.mysql.jdbc.Driver

3 ) ErrorMessageBean.java - klasa wykorzystywana do przekazywania informacji o bledzie z klasy Servlet1 do index.sjp.

4 ) Servlet1.java - po zatwierdzeniu danych formularza na stronie index.sjp nastepuje przekierowanie do Servlet.java
  Tutaj następuje walidacja poprawności wprowadzonych danych przy pomocy metod:
  - nameValidation()
  - peselValidation()
  - emailValidation()
  
  Jesli dane sa poprawne nastepuje proba dodania danych do bazy.
  W wypadku gdy dane nie przejda walidacji lub proba dodania danych do bazy zakonczy sie otrzymaniem komunkatu o 
  bledzie nastepuje przekierowanie do strony index.sjp z informacja o wprawadzaniu niepoprawnych danych
  Jesli dane zostana wprowadzone do bazy nastepuje przekierowanie z strony index.sjp.

5 ) Utitlities.java - klasa wykorzystywana do wypisania osob dodanych juz do bazy danych. Metoda Write() wywolywana 
  jest w pliku index.sjp.
  
6 ) bazamarcin.sql - plik z poleceniami do utworzenia bazy danych.

7 ) style.css - formatowanie stylow dla index.sjp.

