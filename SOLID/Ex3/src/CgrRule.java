public class CgrRule implements EligibilityRule {
    private final RuleInput config;

    public CgrRule(RuleInput config) { this.config = config; }

    public String check(StudentProfile s) {
        if (s.cgr < config.minCgr) return "CGR below " + config.minCgr;
        return null;
    }
}
