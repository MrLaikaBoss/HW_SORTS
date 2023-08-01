import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

public class AviaSoulsTest {

    Ticket[] tickets = new Ticket[0];

    Ticket ticket1 = new Ticket("Moscow", "Saint-Petersburg", 40_000, 18, 23); //5
    Ticket ticket2 = new Ticket("Moscow", "Nizhniy Novgorod", 20_000, 11, 13); // 2
    Ticket ticket3 = new Ticket("Moscow", "Saint-Petersburg", 80_000, 9, 12);  // 3
    Ticket ticket4 = new Ticket("Nizhniy Novgorod", "Kazan", 60_000, 16, 17); // 1
    Ticket ticket5 = new Ticket("Moscow", "Saint-Petersburg", 50_000, 14, 18); // 4

    @Test
    public void whoIsLessNegativeNum() {

        int excepted = -1;
        int actual = ticket2.compareTo(ticket3); //ticket2 меньше чем ticket3 значит -1
        Assertions.assertEquals(excepted, actual);
    }

    @Test
    public void whoIsLessPositiveNum() {
        int excepted = 1;
        int actual = ticket4.compareTo(ticket1); //ticket4 больше чем ticket1 значит 1
        Assertions.assertEquals(excepted, actual);
    }

    @Test
    public void whoIsLessEqualsNum() {
        int excepted = 0;
        int actual = ticket5.compareTo(ticket5); //одинаковые значения, значит 0
        Assertions.assertEquals(excepted, actual);
    }

    @Test
    public void searchSortedByPriceIfFourTickets() {             //тест на поиск с сортировкой по цене
        AviaSouls aviaSouls = new AviaSouls();
        aviaSouls.add(ticket1);
        aviaSouls.add(ticket2);
        aviaSouls.add(ticket3);
        aviaSouls.add(ticket4);
        aviaSouls.add(ticket5);

        Ticket[] excepted = {ticket1, ticket5, ticket3};
        Ticket[] actual = aviaSouls.search("Moscow", "Saint-Petersburg");
        Assertions.assertArrayEquals(excepted, actual);
    }

    @Test
    public void searchSortedByPriceIfOneTicket() {             //тест на поиск с сортировкой по цене
        AviaSouls aviaSouls = new AviaSouls();
        aviaSouls.add(ticket1);
        aviaSouls.add(ticket2);
        aviaSouls.add(ticket3);
        aviaSouls.add(ticket4);
        aviaSouls.add(ticket5);

        Ticket[] excepted = {ticket4};
        Ticket[] actual = aviaSouls.search("Nizhniy Novgorod", "Kazan");
        Assertions.assertArrayEquals(excepted, actual);
    }

    @Test
    public void searchSortedByPriceIfZeroTickets() {             //тест на поиск с сортировкой по цене
        AviaSouls aviaSouls = new AviaSouls();
        aviaSouls.add(ticket1);
        aviaSouls.add(ticket2);
        aviaSouls.add(ticket3);
        aviaSouls.add(ticket4);
        aviaSouls.add(ticket5);

        Ticket[] excepted = {};
        Ticket[] actual = aviaSouls.search("Nizhniy Novgorod", "Moscow");
        Assertions.assertArrayEquals(excepted, actual);
    }

    @Test
    public void sortByTime() {

        TicketTimeComparator ticketTimeComparator = new TicketTimeComparator();
        Ticket[] tickets = {ticket1, ticket2};  //ticket1 5 hours   ticket2 2 hours

        Arrays.sort(tickets, ticketTimeComparator);

        Ticket[] excepted = {ticket2, ticket1};
        Ticket[] actual = tickets;
        Assertions.assertArrayEquals(excepted, actual);
    }

    @Test
    public void anotherSortByTime() {

        TicketTimeComparator ticketTimeComparator = new TicketTimeComparator();
        Ticket[] tickets = {ticket3, ticket4};  //ticket3 3 hours   ticket4 1 hour

        Arrays.sort(tickets, ticketTimeComparator);

        Ticket[] excepted = {ticket4, ticket3};
        Ticket[] actual = tickets;
        Assertions.assertArrayEquals(excepted, actual);
    }

    @Test
    public void searchSortedByTimeIfFiveTickets() {             //тест на поиск с сортировкой по времени перелета
        AviaSouls aviaSouls = new AviaSouls();
        aviaSouls.add(ticket1);
        aviaSouls.add(ticket2);
        aviaSouls.add(ticket3);
        aviaSouls.add(ticket4);
        aviaSouls.add(ticket5);

        TicketTimeComparator ticketTimeComparator = new TicketTimeComparator();

        Ticket[] excepted = {ticket3, ticket5, ticket1};
        Ticket[] actual = aviaSouls.searchAndSortBy("Moscow", "Saint-Petersburg", ticketTimeComparator);
        Assertions.assertArrayEquals(excepted, actual);
    }

    @Test
    public void searchSortedByTimeIfOneTicket() {             //тест на поиск с сортировкой по времени перелета
        AviaSouls aviaSouls = new AviaSouls();
        aviaSouls.add(ticket3);

        TicketTimeComparator ticketTimeComparator = new TicketTimeComparator();

        Ticket[] excepted = {ticket3};
        Ticket[] actual = aviaSouls.searchAndSortBy("Moscow", "Saint-Petersburg", ticketTimeComparator);
        Assertions.assertArrayEquals(excepted, actual);
    }

    @Test
    public void searchSortedByTimeIfZeroTickets() {             //тест на поиск с сортировкой по времени перелета
        AviaSouls aviaSouls = new AviaSouls();

        TicketTimeComparator ticketTimeComparator = new TicketTimeComparator();

        Ticket[] excepted = {};
        Ticket[] actual = aviaSouls.searchAndSortBy("Moscow", "Saint-Petersburg", ticketTimeComparator);
        Assertions.assertArrayEquals(excepted, actual);
    }
}
