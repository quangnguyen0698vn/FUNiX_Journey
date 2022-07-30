import java.time.format.DateTimeFormatter;

/**
 * Theo yêu cầu đề bài, class Employee kế thừa abstract class Staff và implement interface Icalculator
 * thuộc tính số giờ làm thêm extraHours
 * Nếu là cấp nhân viên, base salary sẽ là 3,000,000; lương làn thêm 1 tiếng sẽ là 200,0000 => em khai báo hằng số luôn
 */

public class Employee extends Staff implements ICalculator {
    private int extraHours;
    private static final double BASE_SALARY = 3000000.0;
    private static final double EXTRA_HOUR_RATE = 200000.0;

    /**
     *Hiển thị tất cả thông tin của nhân viên, bao gồm số giờ làm thêm
     *Ở class này mình cũng thực sự implement cho phương thức abstract displayInformation của class cha Staff
     */
    @Override
    public void displayInformation() {
        System.out.println();
        System.out.println("Mã nhân viên: " + this.getStaffCode());
        System.out.println("\tHọ và tên: " + this.getStaffName());
        System.out.println("\tTuổi : " + this.getStaffAge());
        System.out.println("\tHệ số lương: " + this.getCoefficientSalary());
        DateTimeFormatter df = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        System.out.println("\tNgày vào làm: " + df.format(this.getHiredDate()));
        System.out.println("\tBộ phận làm việc: " + this.getDepartment().getDepartmentName());
        System.out.println("\tSố ngày nghỉ phép: " + this.getLeaveDays());
        System.out.println("\tSố giờ làm thêm: " + this.getExtraHours());
    }
    /**
     * @return Đây là phương thức hàm tính lương cho nhân viên, implement interface ICalculator
     */
    @Override
    public double calculateSalary() {
        return this.getCoefficientSalary() * BASE_SALARY + this.getExtraHours() * EXTRA_HOUR_RATE;
    }

    /**
     * hiển thị thông tin về bảng lương của nhân viên
     */
    @Override
    public void displaySalary() {
        System.out.printf("\tLương: " + Math.round(this.calculateSalary()));
    }

    //getter and setter for extraHours field

    public int getExtraHours() {
        return extraHours;
    }

    public void setExtraHours(int extraHours) {
        this.extraHours = extraHours;
    }

    //default constructor
    public Employee() {}
}
