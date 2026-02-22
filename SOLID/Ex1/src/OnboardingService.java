import java.util.List;

public class OnboardingService {
    private final StudentStore store;
    private final RawInputParser parser;
    private final StudentValidator validator;
    private final OnboardingPrinter printer;

    public OnboardingService(StudentStore store, RawInputParser parser, StudentValidator validator, OnboardingPrinter printer) {
        this.store = store;
        this.parser = parser;
        this.validator = validator;
        this.printer = printer;
    }

    public void registerFromRawInput(String raw) {
        printer.printInput(raw);
        ParsedInput input = parser.parse(raw);
        List<String> errors = validator.validate(input);
        if (!errors.isEmpty()) {
            printer.printErrors(errors);
            return;
        }
        String id = IdUtil.nextStudentId(store.count());
        StudentRecord rec = new StudentRecord(id, input.name, input.email, input.phone, input.program);
        store.save(rec);
        printer.printSuccess(id, store.count(), rec);
    }
}
