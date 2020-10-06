package db;

import java.text.DateFormatSymbols;
import java.time.LocalDate;
import java.time.Period;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public class User {
    private Long id;
    private String email;
    private String password;
    private String fullName;
    private Date birthdate;
    private String pictureUrl;

    public User() { }
    public User(Long id, String email, String password, String fullName, Date birthdate, String pictureUrl) {
        this.id = id;
        this.email = email;
        this.password = password;
        this.fullName = fullName;
        this.birthdate = birthdate;
        this.pictureUrl = pictureUrl;
    }
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getEmail() { return email; }
    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }
    public String getFullName() { return fullName; }
    public void setFullName(String fullName) { this.fullName = fullName; }
    public Date getBirthdate() { return birthdate; }
    public void setBirthdate(Date birthdate) { this.birthdate = birthdate; }
    public String getPictureUrl() { return pictureUrl; }
    public void setPictureUrl(String pictureUrl) { this.pictureUrl = pictureUrl; }

    private LocalDate dateToLocalDate(Date date) {
        Calendar c = new GregorianCalendar();
        c.setTime(date);
        return LocalDate.of(c.get(Calendar.YEAR), c.get(Calendar.MONTH) + 1, c.get(Calendar.DAY_OF_MONTH));
    }
    private Period betweenNow(LocalDate localDate) { return Period.between(localDate, LocalDate.now()); }
    private int yearsPassed(Date date) { return betweenNow(dateToLocalDate(date)).getYears(); }
    private int daysPassed(Date date) { return betweenNow(dateToLocalDate(date)).getDays(); }
    private int year(Date date) { return dateToLocalDate(date).getYear(); }
    private int day(Date date) { return dateToLocalDate(date).getDayOfMonth(); }
    private int month(Date date) { return dateToLocalDate(date).getMonthValue(); }
    private String monthName(Date date) {
        String month = new DateFormatSymbols().getMonths()[month(date) - 1];
        return month.substring(0, 1).toUpperCase() + month.substring(1);
    }

    public int getAge() { return yearsPassed(birthdate); }
    public String getBirthday() {
        String birthday = "";
        if (daysPassed(birthdate) == 1) birthday += " tomorrow";
        else birthday += ((day(birthdate) < 10) ? "0" + day(birthdate) : day(birthdate));
        birthday += " " + monthName(birthdate);
        return birthday;
    }
    public String getBirthdayInput() {
        String birthday = "";
        birthday += year(birthdate);
        birthday += "-" + ((month(birthdate) < 10) ? "0" + month(birthdate) : month(birthdate));
        birthday += "-" + ((day(birthdate) < 10) ? "0" + day(birthdate) : day(birthdate));
        return birthday;
    }
}
