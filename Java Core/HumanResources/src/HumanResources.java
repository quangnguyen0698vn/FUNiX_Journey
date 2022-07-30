/**
 * Log console test chương trình
 * https://paste.ubuntu.com/p/CJG7vjZ9KJ/
 * https://paste.ubuntu.com/p/8q4xsDcnV9/
 * có thể xem để hiểu về cách chương trình hoạt động
 * Do đề bài không yêu cầu việc khởi tạo trước hay nhập thông tin các phòng ban
 * Nên em có thêm tính năng số 9 là tính năng thêm phòng ban
 * và trong quá trình nhập thông tin cho nhân viên mới (tính năng 4), cũng có tính năng hỗ
 * trợ việc nhập thêm phòng ban mới
 */

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.*;

public class HumanResources {


    private static final Scanner scanner = new Scanner(System.in);
    /**
     * Danh sách các phòng ban được lưu trong ArrayList<Department>
     */
    private static final ArrayList<Department> departmentList = new ArrayList<>();
    /**
     * Danh sách các nhân viên (bao gồm cả quản lý và nhân viên bình thường) được lưu chung
     * ở ArrayList<Staff> staffList
     * Khi xử lý tính năng sort tăng/giảm lương em gặp khó khăn nên đã workaround bằng cách
     * thêm public abstract double calculateSalary(); vào abstract class Staff
     * vấn đề này có liên quan đến widen type / narrow type (casting)
     */
    private static final ArrayList<Staff> staffList = new ArrayList<>();

    public static void main(String[] args) {

        int userChoice;
        System.out.println("Chào mừng bạn đến với ứng dụng quản lý nhân sự của công ty ABC.");
        System.out.println("Chương trình hiện đang có các tính năng sau đây:");

        do {
            System.out.println();
            System.out.println("1. Hiển thị danh sách nhân viên hiện có trong công ty");
            System.out.println("2. Hiển thị các bộ phận trong công ty");
            System.out.println("3. Hiển thị các nhân viên theo từng bộ phận");
            System.out.println("4. Thêm nhân viên mới vào công ty");
            System.out.println("5. Tìm kiếm thông tin nhân viên theo tên hoặc mã nhân viên");
            System.out.println("6. Hiển thị bảng lương của nhân viên toàn công ty");
            System.out.println("7. Hiển thị bảng lương của nhân viên theo thứ tự tăng dần");
            System.out.println("8. Hiển thị bảng lương của nhân viên theo thứ tự giảm dần");
            System.out.println("9. Thêm bộ phận mới vào công ty");
            System.out.println("10. Thoát khỏi ứng dụng");
            System.out.println();

            System.out.print("Mời bạn chọn tính năng (1-10)? ") ;

            userChoice = Integer.parseInt(scanner.nextLine());

            switch (userChoice) {
                case 1:
                    displayAllEmployeeInformation();
                    break;
                case 2:
                    displayAllDepartments();
                    break;
                case 3:
                    displayAllEmployeeOfEachDepartment();
                    break;
                case 4:
                    addNewEmployee();
                    break;
                case 5:
                    findEmployee();
                    break;
                case 6:
                    displaySalaryTable();
                    break;
                case 7:
                    displayAscendingSalaryTable();
                    break;
                case 8:
                    displayDescendingSalaryTable();
                    break;
                case 9:
                    addNewDepartment();
                    break;
                case 10:
                    return;
                default:
                    break;
            }

            System.out.println();
            System.out.println("Mời bạn tiếp tục tương tác với ứng dụng: ");
        } while(userChoice != 10);
    }

    //1. Hiển thị danh sách nhân viên hiện có trong công ty"
    private static void displayAllEmployeeInformation() {
        if (staffList.size() == 0) {
            System.out.println("Hiện tại công ty chưa có một nhân viên nào!");
            System.out.println("Mời bạn chọn tính năng số 4 để thực hiện việc thêm nhân viên!");
            System.out.println();
            return;
        }
        System.out.println("Danh sách nhân viên của công ty: ");
        printInformation(staffList);
    }

    //2. Hiển thị các bộ phận trong công ty
    private static void displayAllDepartments() {
        if (departmentList.size() == 0) {
            System.out.println("Chưa có dữ liệu về các bộ phận trong công ty.");
            System.out.println("Mời bạn chọn tính năng số 4 để thực hiện việc thêm nhân viên!");
            System.out.println("Hoặc tính năng số 9 để thực hiện việc thêm bộ phận!");
            return;
        }
        System.out.println("Danh sách các bộ phận trong công ty: ");
        int i = 0;
        for (Department dept : departmentList) {
            System.out.println("Bộ phận " + (++i) + ":");
            System.out.println(dept.toString());
        }
        System.out.println();
    }

    //3. Hiển thị các nhân viên theo từng bộ phận
    private static void displayAllEmployeeOfEachDepartment() {
        if (departmentList.size() == 0) {
            System.out.println("Chưa có dữ liệu về các bộ phận trong công ty.");
            System.out.println("Mời bạn chọn tính năng số 4 để thực hiện việc thêm nhân viên!");
            System.out.println("Hoặc tính năng số 9 để thực hiện việc thêm bộ phận!");
            return;
        }
        for (Department dept : departmentList) {

            if (dept.getEmployeeQuantity() == 0) {
                System.out.println("Bộ phận " + dept.getDepartmentName() + " chưa có nhân viên nào!");
                System.out.println();
                continue;
            }

            System.out.println("Danh sách nhân viên của bộ phận: " + dept.getDepartmentName());
            for(Staff emp : staffList) {
                if (emp.getDepartment() == dept) {
                    //Both reference to the same Instance of Department Object,
                    //I do not know if it is a good practice or not, but it works
                    emp.displayInformation();
                    System.out.println();
                }
            }
        }
    }

    //4. Thêm nhân viên mới vào công ty

    /**
     * Hàm thêm nhân viên mới của công ty được xử lý như sau
     * Cho user chọn kiểu nhân viên mới: lính hay là cấp quản lý
     * Các field chung của 2 class Employee và Staff được sử dụng phương thức setter
     * và phương thức input tương ứng (được implement trong chính class HumanResources này) để
     * khởi tạo object
     *
     */
    private static void addNewEmployee() {
        System.out.println();
        System.out.println("a. Thêm nhân viên thông thường.");
        System.out.println("b. Thêm nhân viên là cấp quản lý (có thêm chức vụ).");
        System.out.println("Nếu bạn nhập kí tự khác 'a' và khác 'b', chương trình sẽ quay lại menu chính");
        System.out.print("Bạn chọn chức năng nào (a-b)? ");
        char chooseTitle = scanner.nextLine().toLowerCase().charAt(0);
        if (chooseTitle != 'a' && chooseTitle != 'b') return;

        boolean isManager = chooseTitle == 'b';

        Staff newStaff;
        newStaff = isManager ? new Manager() : new Employee();

        newStaff.setStaffCode(inputEmployeeCode());
        newStaff.setStaffName(inputEmployeeName());
//        int age = inputEmployeeAge();
        newStaff.setStaffAge(inputEmployeeAge());

        if (isManager) {
            //Ép kiểu - narrowing type - casting Staff -> Manager để gọi phương thức setTitle
            //nếu để nguyên kiểu là Staff thì sẽ không truy cập được phương thức setTitle()
            ((Manager) newStaff).setTitle(inputManagerTitle());
        }

        newStaff.setCoefficientSalary(inputCoeficientSalary());
        newStaff.setHiredDate(inputHiredDate());


        newStaff.setDepartment(inputDepartment());
        newStaff.setLeaveDays(inputLeaveDays());

        if (!isManager) {
            //Ép kiểu - narrowing type - casting Staff -> Employee để gọi phương thức set
            //nếu để nguyên kiểu là Staff thì sẽ không truy cập được phương thức setExtraHours()
            ((Employee) newStaff).setExtraHours(inputExtraHours());
        }

        staffList.add(newStaff);

        System.out.println("Chương trình đã thêm nhân viên mới thành công.");
    }

    //5. Tìm kiếm thông tin nhân viên theo tên hoặc mã nhân viên

    /**
     * Logic của phương thức này là tìm kiếm tuyệt đối không phân biết hoa thường
     * ví dụ trong danh sách nhân viên có tên 'Nguyễn Ngọc Quang' thì tìm kiếm với input = 'Quang' sẽ không ra'
     * nhưng 'nguyễn ngọc quang' sẽ ra
     * nếu tìm kiếm theo kiểu tương đổi, em nghĩ mình sẽ sử dụng toLowerCase và phương thức contaín
     * https://www.geeksforgeeks.org/java-string-contains-method-example/
     * Bài tập này funix không cung cấp dữ liệu test case nên thực sự khó để biết nên
     * implement như thế nào.
     */
    private static void findEmployee() {
        System.out.print("Bạn hãy nhập tên hoặc mã số nhân viên cần tìm? ");
        String inputName = scanner.nextLine();
        ArrayList<Staff> target = new ArrayList<>();
        for(Staff emp : staffList)
            if (emp.getStaffCode().equalsIgnoreCase(inputName) || emp.getStaffName().equalsIgnoreCase(inputName))
                target.add(emp);
        if (target.size() == 0) {
            System.out.println("Không có nhân viên nào có tên hoặc mã số khớp với dữ liệu bạn nhập!");
        }
        else {
            System.out.println("Danh sách nhân viên có tên và mã số khớp với dữ liệu truy vấn: ");
            printInformation(target);
        }
    }

    //6. Hiển thị bảng lương của nhân viên toàn công ty
    private static void displaySalaryTable() {
        if (staffList.size() == 0) {
            System.out.println("Hiện tại công ty chưa có một nhân viên nào!");
            System.out.println();
            return;
        }
        System.out.println("Bảng lương của công ty: ");
        printSalary(staffList);
    }

    //https://www.geeksforgeeks.org/comparator-interface-java/

    static class SortbyAscendingSalary implements Comparator<Staff> {

        // Method
        // Sorting in ascending order of roll number
        public int compare(Staff a, Staff b)
        {
            double eps = 0.000000001;
            double sal1 = a.calculateSalary();
            double sal2 = b.calculateSalary();
            if (sal1 + eps < sal2) return  -1;
            if (sal2 + eps > sal2) return 1;
            return 0;
        }
    }

    //7. Hiển thị bảng lương của nhân viên theo thứ tự tăng dần
    private static void displayAscendingSalaryTable() {
        ArrayList<Staff> sortedArr = new ArrayList<>();
        sortedArr.addAll(staffList);
        Collections.sort(sortedArr, new SortbyAscendingSalary());
        System.out.println("Bảng lương theo thứ tự tăng dần: ");
        printSalary(sortedArr);
    }

    //8. Hiển thị bảng lương của nhân viên theo thứ tự giảm dần
    private static void displayDescendingSalaryTable() {
        ArrayList<Staff> sortedArr = new ArrayList<>();
        sortedArr.addAll(staffList);
        Collections.sort(sortedArr, new SortbyAscendingSalary());
        Collections.reverse(sortedArr);
        System.out.println("Bảng lương theo thứ tự giảm dần: ");
        printSalary(sortedArr);
    }

    //7. Hiển thị bảng lương của nhân viên theo thứ tự tăng dần
    /*
    private static void displayIncreaseSalaryTable() {

        double eps = 0.000000001;

        ArrayList<Staff> sortedArr = new ArrayList<>();
        for (Staff st : staffList) sortedArr.add(st);
    */
        //sort with complexity of O(n^2) for simply implement
        /*
        Nếu class Staff không có method calculateSalary() thì compiler sẽ báo lỗi do Staff Reference
        không biết gọi hàm () nào (mặc dù 2 hàm này đã được override ở 2 subclass Employee và Manager)
        workaroudn: thêm public abstract double calculateSalary(); vào Staff Class
         */
    /*
        for (int i = 0; i < sortedArr.size(); i++) {
            for (int j = i + 1; j < sortedArr.size(); j++)
                if (sortedArr.get(i).calculateSalary() >
                        sortedArr.get(j).calculateSalary() + eps) {
                    Staff tmp = sortedArr.get(i);
                    sortedArr.set(i, sortedArr.get(j));
                    sortedArr.set(j, tmp);
                }
        }

        System.out.println("Bảng lương theo thứ tự tăng dần: ");
        printSalary(sortedArr);
    }
    */

    //8. Hiển thị bảng lương của nhân viên theo thứ tự giảm dần

    /*
    private static void displayDecreaseSalaryTable() {
        double eps = 0.000000001;

        ArrayList<Staff> sortedArr = new ArrayList<>();
        for (Staff st : staffList) sortedArr.add(st);

        //sort with complexity of O(n^2) for simply implement
        for (int i = 0; i < sortedArr.size(); i++) {
            for (int j = i + 1; j < sortedArr.size(); j++)
                if (sortedArr.get(i).calculateSalary() + eps <
                        sortedArr.get(j).calculateSalary()) {
                    Staff tmp = sortedArr.get(i);
                    sortedArr.set(i, sortedArr.get(j));
                    sortedArr.set(j, tmp);
                }
        }

        System.out.println("Bảng lương theo thứ tự giảm dần: ");
        printSalary(sortedArr);
    }
    */

    //9. Thêm tên bộ phận

    private static void addNewDepartment() {
        System.out.print("+ Nhập mã bộ phận: ");
        String departmentCode = scanner.nextLine();
        System.out.print("+ Nhập tên bộ phận: ");
        String departmentName = scanner.nextLine();
        for (Department dept : departmentList) {
            if (dept.getDepartmentCode().equalsIgnoreCase(departmentCode)
                    || dept.getDepartmentName().equalsIgnoreCase(departmentName)) {
                System.out.println(("Mã hoặc tên bộ phận bạn vừa nhập đã tồn tại trong hệ thống!"));
                return;
            }
        }
        departmentList.add(new Department(departmentCode, departmentName));
        System.out.println("Bộ phận: " + departmentName + " đã được thêm vào hệ thống thành công!");
    }

    //Helper functions
    private static void printInformation(ArrayList<Staff> arr) {
        //Dùng vòng lặp duyệt qua mảng,
        // và mỗi phần tử thì dùng phương thức displayInformation() để hiển thị thông tin..
        for (Staff st : arr) {
            st.displayInformation();
            System.out.println();
        }
    }

    private static void printSalary(ArrayList<Staff> arr) {
        //Dùng vòng lặp duyệt qua mảng,
        // và mỗi phần tử thì dùng phương thức displayInformation() để hiển thị thông tin.
        //em thêm phương thức displaySalary để hiển thị thêm lương
        for (Staff st : arr) {
            st.displayInformation();
            st.displaySalary();
            System.out.println();
        }
    }

    //Below code is for inputing data

    private static String inputEmployeeCode() {
        System.out.print("+ Nhập mã số nhân viên: ");
        return scanner.nextLine();
    }

    private static String inputEmployeeName() {
        System.out.print("+ Nhập họ và tên của nhân viên: ");
        return scanner.nextLine();
    }

    private static int inputEmployeeAge() {
        System.out.print("+ Nhập tuổi của nhân viên: ");
        return Integer.parseInt(scanner.nextLine());
    }

    private static Manager.EnumTitle inputManagerTitle() {
        System.out.println("+ Nhập chức danh của nhân viên (cấp quản lý): ");
        char option;
        do {
            System.out.println("\t++ Hiện công ty có 3 chức danh quản lý như sau: ");

            System.out.println("\ta. Business Leader");
            System.out.println("\tb. Project Leader");
            System.out.println("\tc. Technical Leader");
            System.out.print("\tXin nhập chức vụ của nhân viên (a-c): ");
            option = scanner.nextLine().toLowerCase().charAt(0);

            if (option < 'a' || option > 'c') {
                System.out.println("Xin vui lòng nhập kí tự 'a', 'b', hoặc 'c' tương ứng với chức danh của nhân viên!");
            }
        } while (option < 'a' || option > 'c');

        switch (option) {
            case 'a':
                return  Manager.EnumTitle.BUSINESS_LEADER;
            case 'b':
                return Manager.EnumTitle.PROJECT_LEADER;
            case 'c':
                return Manager.EnumTitle.TECHNICAL_LEADER;
        }
        return null;
    }

    private static double inputCoeficientSalary() {
        System.out.print("+ Nhập hệ số lương: ");
        return Double.parseDouble(scanner.nextLine());
    }

    /**
     * https://www.baeldung.com/java-8-date-time-intro
     * Việc implement phương thức dưới đây giúp em học được thêm về try catch trong java
     * Người dùng bắt buộc phải nhập đúng định dạng dd-MM-yyyy mới có thể nhập tiếp các field khác
     * Có thể sẽ dễ dàng hơn khi dùng String, nhưng em học được nhiều điều khi tự làm khó bản thân như thế này
     * @return LocalDate
     */
    private static LocalDate inputHiredDate() {

        LocalDate date = null;
        while (date == null) {
            try {
                System.out.print("+ Nhập ngày vào làm (dd-MM-yyyy): ");
                DateTimeFormatter df = DateTimeFormatter.ofPattern("dd-MM-yyyy");
                String strDate = scanner.nextLine();
                date = LocalDate.parse(strDate, df);
            }
            catch (DateTimeParseException e) {
                System.out.println("Bạn làm ơn nhập đúng định dạng dd-MM-yyyy và nhập một ngày đúng trong lịch.");
                System.out.println();
                System.out.println("Đây là một ví dụ: giả sử bạn muốn nhập ngày 1 tháng 2 năm 2022");
                System.out.println("Bạn phải nhập \"01-02-2022\", các cách nhập khác như \"01-2-2022\" hoặc \"01-2-2022\" hoặc \"1-2-2022\"đều không được chấp nhận.");
                System.out.println();
                System.out.println("Xin lỗi vì sự bất tiện này.");
                System.out.println("Mời bạn nhập lại dữ liệu đúng định dạng.");
                System.out.println();
            }
        }
        return date;
    }

    private static Department inputDepartment() {
        Department department = null;
        char option;
        do {
            System.out.print("+ Nhập tên hoặc mã bộ phận làm việc: ");
            String departmentStr = scanner.nextLine();

            for (Department dept : departmentList) {
                if (dept.getDepartmentName().equalsIgnoreCase(departmentStr)
                    || dept.getDepartmentCode().equalsIgnoreCase(departmentStr)) {
                    department = dept;
                    break;
                }
            }

            if (department == null) {
                System.out.println("Bạn vừa nhập một tên hoặc mã bộ phận không có trong hệ thống!");
                System.out.print("Liệu đây có phải là lỗi đánh máy ('y' or 'n')? ");
                option = scanner.nextLine().toLowerCase().charAt(0);
                if (option == 'y') continue;

                System.out.println("Bạn đã xác nhận đây không phải là lỗi đánh máy.");
                System.out.println("Hệ thống sẽ giúp bạn thêm một bộ phận mới cho công ty.");

                String newDepartmentCode;
                String newDepartmentName;

                System.out.println("Xin vui lòng cho biết, chuỗi \"" + departmentStr + "\" bạn vừa nhập là: ");
                System.out.println("\ta. Mã bộ phận");
                System.out.println("\tb. Tên bộ phận");
                System.out.print("(a-b)? ");
                option = scanner.nextLine().toLowerCase().charAt(0);

                if (option == 'a') {
                    newDepartmentCode = departmentStr;
                    System.out.print("\t+ Xin bạn vui lòng nhập thêm tên bộ phận: ");
                    newDepartmentName = scanner.nextLine();
                }
                else
                if (option == 'b') {
                    newDepartmentName = departmentStr;
                    System.out.print("\t+ Xin bạn vui lòng nhập thêm mã bộ phận: ");
                    newDepartmentCode = scanner.nextLine();
                }
                else {
                    continue;
                }

                department = new Department(newDepartmentCode, newDepartmentName);
                departmentList.add(department);
                System.out.println("Bộ phận mới: " + department.getDepartmentName() + " được tạo thành công!");
                System.out.println();
            }
        } while (department == null);

        return department;
    }

    private static int inputLeaveDays() {
        System.out.print("+ Nhập số ngày nghỉ phép: ");
        return Integer.parseInt(scanner.nextLine());
    }

    private static int inputExtraHours() {
        System.out.print("+ Nhập số giờ nhân viên làm thêm trong tháng: ");
        return Integer.parseInt(scanner.nextLine());
    }

}
