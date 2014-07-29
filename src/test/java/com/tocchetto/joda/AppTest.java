package com.tocchetto.joda;

import org.joda.time.DateTime;
import org.joda.time.DateTimeZone;
import org.joda.time.Hours;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;
import org.joda.time.format.ISODateTimeFormat;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;


/**
 * Unit test for simple App.
 */
public class AppTest {

    private DateTimeZone timeZone;

    @Test(groups = { "fast" })
    public void aFastTest() {
        System.out.println("Fast test");
    }

    @Test(groups = {"slow"})
    public void aSlowTest() {
        System.out.println("Slow test");
    }

    @Test(groups = {"fast"})
    public void calculaDiferencaUmaHora() {
        // Configurando a data referência DateTimeZone
        // DateTimeZone timeZone = DateTimeZone.forID("GMT");

        System.out.println(DateTime.now().getYear());

        DateTime dateTime1 = new DateTime(1970, 01, 01, 00, 00, 00, timeZone);
        // Deve ser 0
        System.out.println("Diferenca em milisegundos = " + dateTime1.getMillis());

        // Configurando uma hora de diferença
        DateTime dateTime2 = dateTime1.plusHours(1);

        int hoursDiff = Hours.hoursBetween(dateTime1, dateTime2).getHours();
        // Deve ser de 1 hora = 3600000 milisegundos
        System.out.println("Diferenca em milisegundos = " + dateTime2.getMillis());
        System.out.println("Diferenca em horas = " + Hours.hoursBetween(dateTime1, dateTime2).getHours());

        Assert.assertEquals(hoursDiff, 1, "A diferenca calculada deveria ser exatamente 1 hora");

        // Leia mais em: Trabalhando com Joda-Time
        // http://www.devmedia.com.br/trabalhando-com-joda-time/26524
    }

    @Test(groups = {"fast"})
    public void realizaParseDataComDiaMesAnoHoraEMinutos() {
        DateTime dateTime = new DateTime(1970, 01, 01, 00, 00, 00, timeZone);
        DateTimeFormatter fmt = DateTimeFormat.forPattern("dd/MM/YYYY HH:mm").withZone(timeZone);

        // Alternativa 1
        System.out.println(fmt.print(dateTime));

        // Alternativa 2
        System.out.println(dateTime.toString(fmt));

        String dateAsText = "28/07/2014 09:57";
        // Efetuando parse da string no formato "dd/MM/YYYY HH:mm"
        dateTime = fmt.parseDateTime(dateAsText);

        String parsedDateAsText = dateTime.toString(fmt);
        System.out.println(parsedDateAsText);

        Assert.assertEquals(parsedDateAsText, dateAsText, "As datas no formato texto deveriam ser iguais");

        // Imprimindo no formato ISO8601
        fmt = ISODateTimeFormat.dateTime();
        System.out.println(fmt.print(dateTime));
    }

    @BeforeClass
    public void setUp() {
        timeZone = DateTimeZone.forID("America/Sao_Paulo");
    }

}
