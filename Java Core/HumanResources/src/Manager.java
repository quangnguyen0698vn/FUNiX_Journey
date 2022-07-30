import java.time.format.DateTimeFormatter;
/**
 * Theo yêu cầu đề bài, class Manager kế thừa abstract class Staff và implement interface Icalculator
 * Nếu là cấp quản lý, base salary sẽ là 5,000,000; em khai báo hằng số luôn
 * Cấp quản lý sẽ có field title (chức danh), cách tiếp cận đơn giản là dùng String và dùng lệnh if để
 * tính mức lương trách nhiệm, trong quá trình làm em lại có ý sủ dụng Enum dể học thêm về java và đã
 * implement nó luôn
 */
public class Manager extends Staff implements ICalculator {

    private static final double BASE_SALARY = 5000000.0;

    private EnumTitle title;

    public EnumTitle getTitle() {
        return title;
    }


    public void setTitle(EnumTitle title) {
        this.title = title;
    }

    public Manager() {}

    /**
     * @return Lương của cấp quản lý
     */
    @Override
    public double calculateSalary() {
        return getCoefficientSalary() * BASE_SALARY + getTitle().getResponsibilitySalary();
    }

    /**
     * hiển thị thông tin của cấp quản lý
     */
    @Override
    public void displayInformation() {
        System.out.println();
        System.out.println("Mã nhân viên: " + this.getStaffCode());
        System.out.println("\tHọ và tên: " + this.getStaffName());
        System.out.println("\tTuổi : " + this.getStaffAge());
        System.out.println("\tChức vụ: " + getTitle().getTitleName());
        System.out.println("\tHệ số lương: " + this.getCoefficientSalary());
        DateTimeFormatter df = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        System.out.println("\tNgày vào làm: " + df.format(this.getHiredDate()));
        System.out.println("\tBộ phận làm việc: " + this.getDepartment().getDepartmentName());
        System.out.println("\tSố ngày nghỉ phép: " + this.getLeaveDays());
    }

    /**
     * hiển thị thông tin về bảng lương của cấp quản lý
     */
    @Override
    public void displaySalary() {
        System.out.printf("\tLương: " + Math.round(this.calculateSalary()));
    }

    /**
     * Em có tham khảo về Enum tại https://www.baeldung.com/a-guide-to-java-enums
     * và quyết định sẽ thử sử dụng nó cho việc tính lương cho Manager
     * Quản lý: Hệ số lương * 5,000,000 + lương trách nhiệm
     * Lương trách nhiệm:
     * Business Leader = 8,000,000
     * Project Leader = 5,000,000
     * Technical Leader = 6,000,000
     * Tất cả đều được định nghĩa trong enum EnumTitle
     */
    public enum EnumTitle {
        BUSINESS_LEADER("Business Leader", 8000000),
        PROJECT_LEADER("Project Leader", 5000000),
        TECHNICAL_LEADER("Technical Leader", 6000000);
        private final double responsibilitySalary;
        private final String titleName;
        EnumTitle(String titleName, double responsibilitySalary) {
            this.titleName = titleName;
            this.responsibilitySalary = responsibilitySalary;
        }

        public double getResponsibilitySalary() {
            return responsibilitySalary;
        }

        public String getTitleName() {
            return titleName;
        }
    }

}
