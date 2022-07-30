import java.time.LocalDate;

/**
 * Staff.java là class abstract chứa các thông tin cơ bản của nhân viên
 * staffCode là mã nhân viên
 * staffName là  tên nhân viên
 * staffAge là tuổi nhân viên
 * coeficientSalary là hệ số lương
 * hiredDate là ngày vào làm
 * department là bộ phận làm việc
 * leaveDays là số ngày nghỉ phép
 *
 * Làm đúng theo yêu cầu đề bài, em đã:
 * class Staff là abstract class
 * phương thức displayInformation cũng là abstract
 * Tham khảo ở https://blog.udemy.com/java-abstract-class/
 * em implement các phương thức setter/getter cho các private filed trong class abstract này
 * mặc dù field hiredDate có thể lưu trữ  dễ dàng bằng String, em có thử làm với Object LocalDate
 * để rèn luyện thêm
 */


public abstract class Staff {
    private String staffCode;
    private String staffName;
    private int staffAge;
    private double coefficientSalary;
    private LocalDate hiredDate;
    private Department department;
    private int leaveDays;
    public abstract void displayInformation();
    public abstract void displaySalary();

    //phương thức abstract dưới đây cần thiết để em thực hiện tính năng sort danh sách nhân viên tăng/giảm theo lương
    public abstract double calculateSalary();

    public String getStaffCode() {
        return staffCode;
    }

    public void setStaffCode(String staffCode) {
        this.staffCode = staffCode;
    }

    public String getStaffName() {
        return staffName;
    }

    public void setStaffName(String staffName) {
        this.staffName = staffName;
    }

    public int getStaffAge() {
        return staffAge;
    }

    public void setStaffAge(int staffAge) {
        this.staffAge = staffAge;
    }

    public double getCoefficientSalary() {
        return coefficientSalary;
    }

    public void setCoefficientSalary(double coefficientSalary) {
        this.coefficientSalary = coefficientSalary;
    }

    public LocalDate getHiredDate() {
        return hiredDate;
    }

    public void setHiredDate(LocalDate hiredDate) {
        this.hiredDate = hiredDate;
    }

    public Department getDepartment() {
        return department;
    }

    public void setDepartment(Department department) {
        this.department = department;
        //Tăng số lượng nhân viên của phòng ban khi khởi tạo một instance mới của class Staff này.
        department.setEmployeeQuantity(department.getEmployeeQuantity()+1);
    }

    public int getLeaveDays() {
        return leaveDays;
    }

    public void setLeaveDays(int leaveDays) {
        this.leaveDays = leaveDays;
    }
}
