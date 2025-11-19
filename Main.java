// 1. Product 클래스: 최종적으로 생성될 객체
static class Computer {
    // 필수 요소
    private final String cpu;
    private final int ramGB;
    // 선택 요소
    private final String screenResolution;

    // 생성자는 오직 Builder 객체만 받을 수 있도록 private으로 설정
    private Computer(Builder builder) {
        this.cpu = builder.cpu;
        this.ramGB = builder.ramGB;
        this.screenResolution = builder.screenResolution;
    }

    @Override
    public String toString() {
        return "Computer [CPU=" + cpu + ", RAM=" + ramGB + "GB, Screen=" + screenResolution + "]";
    }

    // 2. Builder 클래스 (정적 중첩 클래스): 내부적으로 Product를 생성
    public static class Builder {
        
        private final String cpu;
        private final int ramGB;

        private String screenResolution = "미설정";

        public Builder(String cpu, int ramGB) {
            this.cpu = cpu; this.ramGB = ramGB;
        }

        public Builder setScreenResolution(String resolution) {
            this.screenResolution = resolution;
            return this;
        }

        public Computer build() {
            return new Computer(this);
        }
    }
}

void main() {
    IO.println("--- Builder 패턴 활용 예제 ---");

    // 1. 모든 옵션을 설정한 복잡한 컴퓨터 생성 (가독성 우수)
    Computer gamingPC = new Computer.Builder("Intel i9", 32).setScreenResolution("4K OLED").build();

    IO.println("게이밍 PC: " + gamingPC);

    // 2. 선택 옵션 없이 필수 요소만 설정한 일반 컴퓨터 생성
    Computer basicPC = new Computer.Builder("AMD Ryzen 5", 16)
            // setScreenResolution 메소드를 호출하지 않아도 됨
            .build();

    IO.println("베이직 PC: " + basicPC);
}
