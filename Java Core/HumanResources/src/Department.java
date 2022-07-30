/**
 * class Department gồm 3 fields: mã bộ phận, tên bộ phận, và số lượng nhân viên của bộ phận
 * Hàm toString() được override theo yêu cầu của đề bài
 */
public class Department {
    private String departmentCode;
    private String departmentName;
    private int employeeQuantity;

    @Override
    public String toString() {
        return "\t Mã bộ phận: " + getDepartmentCode() + "\n\t Tên bộ phận: "
                + getDepartmentName() + "\n\t Số lượng nhân viên: " + getEmployeeQuantity();
    }

    public Department(String code, String name, int people) {
        setDepartmentCode(code);
        setDepartmentName(name);
        setEmployeeQuantity(people);
    }

    public Department(String code, String name) {
        this(code, name, 0);
    }

    public String getDepartmentCode() {
        return departmentCode;
    }

    public void setDepartmentCode(String departmentCode) {
        this.departmentCode = departmentCode;
    }

    public String getDepartmentName() {
        return departmentName;
    }

    public void setDepartmentName(String departmentName) {
        this.departmentName = departmentName;
    }

    public int getEmployeeQuantity() {
        return employeeQuantity;
    }

    public void setEmployeeQuantity(int employeeQuantity) {
        this.employeeQuantity = employeeQuantity;
    }
}
