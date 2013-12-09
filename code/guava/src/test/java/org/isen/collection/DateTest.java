package org.isen.collection;

import static org.assertj.core.api.Assertions.assertThat;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import org.joda.time.DateTime;
import org.junit.Test;

public class DateTest {
    private static final String DATE_FORMAT = "yyyy/MM/dd HH:mm";
    private SimpleDateFormat sdf = new SimpleDateFormat(DATE_FORMAT);

    @Test
    public void iCanInstanciateADateWithJDK() throws Exception {
        Calendar calendar = Calendar.getInstance();
        calendar.set(2010, Calendar.DECEMBER, 13, 0,0,0);
        assertThat(sdf.format(calendar.getTime())).isEqualTo("2010/12/13 00:00");
    }

    @Test
    public void iCanInstanciateADateWithJoda() throws Exception {
        DateTime date = new DateTime(2010,12,13,0,0,0,0);
        assertThat(date.toString(DATE_FORMAT)).isEqualTo("2010/12/13 00:00");
    }

    @Test
    public void iCanAddDaysToDateWithJDK() throws Exception {
        Calendar calendar = Calendar.getInstance();
        calendar.set(2010, Calendar.DECEMBER, 13, 0,0,0);
        calendar.add(Calendar.DAY_OF_MONTH, 90);
        assertThat(sdf.format(calendar.getTime())).isEqualTo("2011/03/13 00:00");
    }

    @Test
    public void iCanAddDaysToDateWithJoda() throws Exception {
        DateTime date = new DateTime(2010,12,13,0,0,0,0).plusDays(90);
        assertThat(date.toString(DATE_FORMAT)).isEqualTo("2011/03/13 00:00");
    }

    @Test
    public void complexCalculationWithJoda() throws Exception {
        DateTime dateTime = new DateTime(2010,12,13,0,0,0,0);
        assertThat(dateTime.plusDays(45).plusMonths(1).dayOfWeek()
          .withMaximumValue().toString(DATE_FORMAT)).isEqualTo("2011/02/27 00:00");
    }


    @Test
    public void testFromJodaToJDK() throws Exception {
        DateTime dateTime = new DateTime(2010,12,13,0,0,0,0);
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(dateTime.toDate());
        assertThat(sdf.format(calendar.getTime())).isEqualTo("2010/12/13 00:00");
    }

    @Test
    public void testFromJDKToJoda() throws Exception {
        Calendar calendar = Calendar.getInstance();
        calendar.set(2010, Calendar.DECEMBER, 13, 0,0,0);
        DateTime dateTime = new DateTime(calendar);
        assertThat(dateTime.toString(DATE_FORMAT)).isEqualTo("2010/12/13 00:00");
    }


}
