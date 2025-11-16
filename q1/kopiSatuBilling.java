import java.util.*;
public class kopiSatuBilling {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        double subtotal = 0.0;
        double price;

        System.out.println("Kopi-Satu Billing System ");

        int itemCount = 0;
        while (true) {
            System.out.print("Enter item price (0 to finish): ");
            price = sc.nextDouble();

            if (price < 0) {
                System.out.println("Invalid amount. Price cannot be negative. Please re-enter.");
                continue;
            } else if (price == 0) {
                if (itemCount == 0) {
                    System.out.println("Please enter at least one valid item before finishing.");
                    continue;
                } else break;
            } else {
                subtotal += price;
                itemCount++;
            }
        }

        sc.nextLine();
        String day;
        int hour;
        while (true) {
            System.out.print("Enter day of week: ");
            day = sc.nextLine().trim().toLowerCase();
            List<String> validDays = Arrays.asList(
                    "monday", "tuesday", "wednesday", "thursday", "friday", "saturday", "sunday");
            if (!validDays.contains(day)) {
                System.out.println("Invalid day. Please enter a valid day (e.g., Monday).");
                continue;
            }
            break;
        }

        while (true) {
            System.out.print("Enter hour (24-hour format): ");
            hour = sc.nextInt();
            if (hour < 0 || hour > 23) {
                System.out.println("Invalid hour. Please enter a valid hour (0–23).");
                continue;
            }
            break;
        }

        sc.nextLine();
        System.out.print("Is customer a member (Y/N)? ");
        char member = sc.nextLine().trim().toUpperCase().charAt(0);

        double sstRate;
        if (subtotal <= 30) sstRate = 0.06;
        else if (subtotal <= 100) sstRate = 0.08;
        else sstRate = 0.10;

        double serviceTax = subtotal * sstRate;
        double totalBeforeDiscount = subtotal + serviceTax;

        double studentDiscount = 0, happyHourDiscount = 0, weekendDiscount = 0;
        boolean isWeekday = day.equals("monday") || day.equals("tuesday") ||
                day.equals("wednesday") || day.equals("thursday") ||
                day.equals("friday");
        boolean isWeekend = day.equals("saturday") || day.equals("sunday");

        if (isWeekday && totalBeforeDiscount > 25) {
            studentDiscount = totalBeforeDiscount * 0.10;
        }

        if (isWeekday && hour >= 15 && hour <= 16) {
            happyHourDiscount = (totalBeforeDiscount - studentDiscount) * 0.05;
        }

        if (isWeekend && subtotal >= 50) {
            weekendDiscount = totalBeforeDiscount * 0.05;
        }

        double totalDiscount = studentDiscount + happyHourDiscount + weekendDiscount;
        double totalPayable = totalBeforeDiscount - totalDiscount;

        double cashback = 0;
        if (member == 'Y') {
            cashback = totalPayable * 0.02;
        }

        System.out.println("Kopi-Satu Receipt");
        System.out.printf("Subtotal: RM %.2f%n", subtotal);
        System.out.printf("Service Tax (%.0f%%): RM %.2f%n", sstRate * 100, serviceTax);
        System.out.printf("Total before discount: RM %.2f%n", totalBeforeDiscount);
        if (studentDiscount > 0) System.out.printf("Student Discount (10%%): RM %.2f%n", studentDiscount);
        if (happyHourDiscount > 0) System.out.printf("Happy Hour Discount (5%%): RM %.2f%n", happyHourDiscount);
        if (weekendDiscount > 0) System.out.printf("Weekend Discount (5%%): RM %.2f%n", weekendDiscount);
        System.out.printf("Total Payable: RM %.2f%n", totalPayable);
        if (cashback > 0) System.out.printf("Loyalty Cashback (2%%): RM %.2f%n", cashback);
        System.out.printf("Final Amount to Collect: RM %.2f%n", totalPayable);
    }
}
