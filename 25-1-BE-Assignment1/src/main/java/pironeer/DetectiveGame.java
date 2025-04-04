package pironeer;

import java.util.*;
import java.util.stream.Collectors;

import pironeer.util.Reader;
import pironeer.util.Timer;

public class DetectiveGame {

    private List<Character> characters; // 등장인물
    private Character murderer; // 범인
    private Character victim; // 피해자
    private String dyingMessage; // 다잉메시지
    private Character suspect; // 용의자
    private int lives; // 기회
    private String detectiveName; // 형사

    private final Reader reader = new Reader(); // 문자열 출력
    private final Timer timer = new Timer(); // 스레드 조절 timer
    private final Random random = new Random(); // 랜덤 난수

    // 초기 세팅
    public void init() {
        characters = new ArrayList<>();
        characters.add(new Character("최승호", "장발이야", "운동복을 입었어", "나이키를 신었어"));
        characters.add(new Character("김민수", "파란 모자를 썼어", "양복을 입었어", "구두를 신었어"));
        characters.add(new Character("이건희", "파마를 했어", "무스탕을 입었어", "아무것도 안 신고 있었어"));
        characters.add(new Character("손관우", "스님 머리야", "셔츠를 입었어", "슬리퍼 신었어"));
        characters.add(new Character("장지요", "단발머리야", "치마를 입었어", "부츠를 신었어"));
        characters.add(new Character("안시환", "투블럭을 했어", "반팔티를 입었어", "크록스를 신었어"));
        characters.add(new Character("이지현", "허리까지 머리카락이 있어", "원피스를 입었어", "힐을 신었어"));
        lives = 2;
    }

    // 4. Reader 클래스를 사용하여 탐정의 이름을 입력받고 1.5초 정지
    private void inputDetectiveName() {
        Reader reader = new Reader(); // Reader 클래스 사용
        System.out.println("\n탐정님의 이름을 알려주세요: ");
        detectiveName = reader.nextLine().trim(); // reader를 이용해 탐정의 이름을 받음, trim()을 사용해 앞뒤 공백 제거
        System.out.println("탐정: " + detectiveName); // 탐정의 이름 출력
        Timer timer = new Timer(); // Timer 클래스 사용
        timer.sleep(1500); // 1500 ms 만큼 스레드 정지
    }

    // 5. 캐릭터 중 한 명을 희생자로 지정하고, 목록에서 제거
    private void selectVictim() {
        int randomNum = random.nextInt(characters.size()); // 0 ~ (캐릭터의 수 - 1) 사이의 난수
        victim = characters.get(randomNum); // 피해자 랜덤으로 지정

        characters.remove(randomNum); // 피해자를 character에서 제거
    }

    // 6. 랜덤하게 속성 값을 선택하고 다잉메시지 출력
    private void selectDyingMessage() {
//        int randomDyingMessageTypeIndex = random.nextInt(dyingMessageType.size()); // 0 ~ (dyingMessageType - 1) 사이의 난수
//        randomDyingMessageTypeIndex += 1; // characters의 각각의 요소의 타입에 맞는 index로 변환
//        // -> index 뽑았으니 바로 범인의 특징 뽑기
//        if (randomDyingMessageTypeIndex == 1) {
//            dyingMessage = "머리스타일은 " + murderer.getHair() + " 윽..☠";
//        }
//        else if (randomDyingMessageTypeIndex == 2) {
//            dyingMessage = "옷은 " + murderer.getClothes() + " 윽..☠";
//        }
//        else {
//            dyingMessage = "신발은 " + murderer.getShoes() + " 윽..☠";
//        }

        // Enum 사용 및 switch case 사용
        enum DyingMessageType {
            HAIR,
            CLOTHES,
            SHOES,
        }

        DyingMessageType type = DyingMessageType.values()[
                random.nextInt(DyingMessageType.values().length)
                ];

        switch (type) {
            case HAIR:
                dyingMessage = "머리스타일은 " + murderer.getHair() + " 윽..☠";
                break;

            case CLOTHES:
                dyingMessage = "옷은 " + murderer.getClothes() + " 윽..☠";
                break;

            case SHOES:
                dyingMessage = "신발은 " + murderer.getShoes() + " 윽..☠";
                break;

            default:
                dyingMessage = null;
        }
    }

    public void showIntro() {
        System.out.println("탐정 게임에 오신 것을 환영합니다.");
        timer.sleep(1000);
        inputDetectiveName(); // 4번

        selectVictim(); // 5번

        murderer = characters.get(random.nextInt(characters.size())); // 범인 랜덤으로 지정

        selectDyingMessage(); // 6번

        System.out.println("########################################");
        System.out.println("#######        평화로운 해커톤              ");
        System.out.println("########################################");
        timer.sleep(1500);

        System.out.println("\n코딩에 몰두하던 " + detectiveName + ", 눈이 피로해지기 시작한다. 해커톤의 열기가 고조될수록, 정신은 점점 흐릿해진다.");
        timer.sleep(1000);
        System.out.println(detectiveName + ": 이 해커톤, 너무 평화롭기만 하군... 뭔가 재밌는 사건이라도 터져야 할 텐데. 뭐, 코드가 몽땅 날아가는 일이라든지.");
        timer.sleep(1000);
        System.out.println(victim.getName() + ": 하하, 탐정님! 그런 무서운 말씀은 제발 그만하세요. 상상만 해도 아찔하네요... 그런 일은 절대로 일어나지 않을 거예요.");
        timer.sleep(1000);
        System.out.println(detectiveName + ": 뭐, 그렇긴 하겠지. 아, 참고로 나는 명탐정 " + detectiveName + "! 사건이 터지면 언제든 나를 찾아.");
        timer.sleep(1000);
        System.out.println(victim.getName() + ": 하하, 명탐정님! 알겠습니다. 그런데 요즘 제 노트북을 자꾸 누가 훔쳐보는 것 같아서 신경 쓰이긴 해요. 집 앞 카페에서 코딩하다 보면 말이죠...");
        timer.sleep(1000);
        System.out.println("그렇게 둘은 헤어졌고, 그로부터 10분 후 갑자기 정전이 일어나게 되는데..\n");
        timer.sleep(1000);

        System.out.println("########################################");
        System.out.println("#######        사건 발생             ");
        System.out.println("########################################");
        timer.sleep(1000);

        System.out.println("\n갑자기 날카로운 비명 소리가 울려 퍼졌다. " + victim.getName() + "씨의 노트북 화면이 순식간에 블루스크린으로 바뀌었다.");
        timer.sleep(1000);
        System.out.println("그 충격에 " + victim.getName() + "씨는 정신을 잃고 그대로 쓰러졌다...");
        timer.sleep(1000);

        System.out.println("\n현장은 순식간에 혼란에 빠졌고, 바닥에는 " + victim.getName() + "씨의 메시지가 남겨져 있었다.");
        timer.sleep(1000);

        System.out.println("\n================사망하지는 않았지만 이게 다잉메시지?!================");
        System.out.println("                    \"" + dyingMessage + "\"");
        System.out.println("=================================================================");
        timer.sleep(1000);

        // 7. 용의자 총 인원수 출력
        // System.out.println("\n문제의 노트북 주위에 있는 사람은 " + {7번} + "명입니다.");
        // characters의 수 출력 -> Stream API 사용?
        List<String> names = Optional.ofNullable(characters) // Optional 사용
                        .orElse(Collections.emptyList())
                        .stream()
                        .map(Character::getName)
                        .collect(Collectors.toList()); // Stream API 사용

        System.out.println("\n문제의 노트북 주위에 있는 사람은 " + names.size() + "명입니다.");
        timer.sleep(1000);

        System.out.println("그중, 범인은 바로 이 자리에 있을 것입니다...");
        timer.sleep(1000);
        System.out.println(detectiveName + "님의 임무는 범인을 찾아내는 것입니다. 진실을 밝혀내세요. 기회는 2번입니다.\n");
        timer.sleep(1000);

        System.out.println("########################################");
        System.out.println("#######        추리 시작               ");
        System.out.println("########################################");
        timer.sleep(1000);
    }

    public void investigate() {
        System.out.println("용의자와 대화를 나누고 인상착의를 수집하세요...");
        for (int i = 1; i <= characters.size(); i++) {
            System.out.println(i + ". " + characters.get(i - 1).getName());
        }

        System.out.println("\n누구를 조사하시겠습니까? 이름을 입력하세요: ");
        String choiceName = reader.nextLine().trim(); // 문자열을 입력 받고 앞뒤 공백을 제거, trim() 메소드는 앞뒤 공백을 제거한 문자열의 복사본을 리턴

        // 8. 사용자가 입력한 이름을 가진 용의자 조사
        // choiceName: 사용자가 입력한 용의자 => Stream API를 사용해 필터를 걸어 해당 용의자 정보 출력
        // character의 0번 index의 값과 choiceName이 동일한지 비교
        suspect = characters.stream()
                .filter(character -> character.getName().equals(choiceName)) // player가 입력한 용의자의 이름과 동일한 인물 filtering
                        .findFirst() // 이름이 동일한 character(객체)가 있다면 첫 번째 결과 가져옴!
                                .orElse(null); // 동일한 이름이 없을 시 null 반환

        if (suspect != null) {
            System.out.println("용의자: " + suspect.getName() + "의 인상착의");
            System.out.println("    상의: " + suspect.getHair());
            System.out.println("    하의: " + suspect.getClothes());
            System.out.println("    신발: " + suspect.getShoes());
            return;
        }

        System.out.println("잘못된 입력입니다! 시간이 얼마 남지 않았습니다, 다시 시도해주세요!");
        System.out.println("범인은 아직도 우리 곁에 있어요. 서둘러 진실을 밝혀내야 합니다!");
        System.out.println(detectiveName + ": 좋아, 이번엔 잘 선택해보자.");

        investigate(); // 재귀호출
    }

    public boolean matchDyingMessage(Character character) {
        if (dyingMessage.equals("머리스타일은 " + character.getHair() + " 윽..☠") || // murderer -> character로 수정
                dyingMessage.equals("옷은 " + character.getClothes() + " 윽..☠") ||
                dyingMessage.equals("신발은 " + character.getShoes() + " 윽..☠")) {
            return true;
        }
        return false;
    }

    public String promptChoice(String prompt) {
        while (true) {
            System.out.println(prompt);
            String choice = reader.nextLine();
            if (choice.equals("네") || choice.equals("아니오")) {
                return choice;
            }
            System.out.println("잘못된 입력입니다. 네 또는 아니오만 입력해 주세요.");
        }
    }

    public void accuse() {
        System.out.println("\n범인을 지목할 시간입니다.");

        // 10. charaters 각 항목을 인덱스와 함께 출력
//        for (int i = 0; i < characters.size(); i++){
//            System.out.println(i + ". " + characters.get(i).getName() + characters.get(i).getHair() + characters.get(i).getClothes() + characters.get(i).getShoes() + "\n");
//        }

        // 위 코드 개선 버전
        for (int i = 1; i <= characters.size(); i++){
            Character character = characters.get(i - 1); // 위의 코드에서 반복되는 get(i)를 해결
            System.out.println(i + ". " + character.getName());
        }

        System.out.println("\n누구를 범인으로 지목하시겠습니까? 이름을 입력하세요: ");
        String choiceName = reader.nextLine().trim();
        System.out.println(detectiveName + ": 범인은 바로 " + choiceName + "씨야");

        for (Character character : characters) {
            if (character.getName().equals(choiceName)) {
                suspect = character;
                checkOutcome();
                return;
            }
        }

        System.out.println("탐정님, 그건 잘못된 선택입니다! 시간이 얼마 남지 않았습니다, 다시 시도해주세요!");
        System.out.println("범인은 아직도 우리 곁에 있어요. 서둘러 진실을 밝혀내야 합니다!");
        System.out.println(detectiveName + ": 좋아, 이번엔 잘 선택해보자.\n");
        timer.sleep(1000);
        accuse();
    }

    public void checkOutcome() {
        if (matchDyingMessage(suspect)) {
            System.out.println("""
            ----------------------------------------------------------------------------------
            
            
                 ██████╗  █████╗ ███╗   ███╗███████╗    ██╗    ██╗██╗███╗   ██╗
                ██╔════╝ ██╔══██╗████╗ ████║██╔════╝    ██║    ██║██║████╗  ██║
                ██║  ███╗███████║██╔████╔██║█████╗      ██║ █╗ ██║██║██╔██╗ ██║
                ██║   ██║██╔══██║██║╚██╔╝██║██╔══╝      ██║███╗██║██║██║╚██╗██║
                ╚██████╔╝██║  ██║██║ ╚═╝ ██║███████╗    ╚███╔███╔╝██║██║ ╚████║
                 ╚═════╝ ╚═╝  ╚═╝╚═╝     ╚═╝╚══════╝     ╚══╝╚══╝ ╚═╝╚═╝  ╚═══╝
            
            
            ----------------------------------------------------------------------------------
            """);
            System.out.println("########################################");
            System.out.println("#######        당신은 역시 명탐정!!         ");
            System.out.println("########################################");
            System.out.println("정답입니다! 범인은 바로 " + suspect.getName() + "씨였습니다!");
            timer.sleep(1000);
            System.out.println("당신의 추리는 완벽했습니다, 역시 명탐정 " + detectiveName + " 답군요.");
            timer.sleep(1000);
            System.out.println("\n" + detectiveName + ": 왜 노트북을 망가뜨렸습니까?? " + victim.getName() + "씨의 노트북을 왜 그렇게 했죠?");
            timer.sleep(1000);
            System.out.println(suspect.getName() + ": 그게 사실... " + victim.getName() + "씨의 팀이 해커톤 우승을 못하게 하려고... 그래서 홧김에.. 죄송합니다🥹");
            timer.sleep(1000);
            System.out.println("\n" + suspect.getName() + "씨는 끝내 자신의 범행을 인정했고, 피로그래밍 22기 해커톤에서 퇴출당했습니다.");
            timer.sleep(1000);
            System.out.println("사건은 해결되었고, 모든 사람들이 안도의 한숨을 내쉬었습니다. 당신의 활약 덕분입니다.");
            timer.sleep(1000);
            askRestart();
        } else {
            lives--;
            if (lives > 0) {
                System.out.println(suspect.getName() + ": 무슨 소리야? 내 인상착의를 봐... 당신 명탐정 맞아? 💢💢💢");
                System.out.println("\n틀렸습니다... " + suspect.getName() + "씨는 범인이 아닙니다. 남은 기회는 " + lives + "번입니다.");
                System.out.println("시간이 얼마 남지 않았어요. 신중하게 선택해주세요.");
                timer.sleep(1000);
                String choice = promptChoice("용의자들의 인상착의를 다시 보겠습니까? (네/아니오): ");
                if (choice.equals("네")) {
                    System.out.println(detectiveName + ": 좋았어... 다시 차근차근 보자\n");
                    timer.sleep(1000);
                    mainFlow();
                } else {
                    accuse();
                }
            } else {
                System.out.println(
                        """
                ----------------------------------------------------------------------------------
                
                    ██████╗  █████╗ ███╗   ███╗███████╗     ██████╗ ██╗   ██╗███████╗██████╗ 
                    ██╔════╝ ██╔══██╗████╗ ████║██╔════╝    ██╔═══██╗██║   ██║██╔════╝██╔══██╗
                    ██║  ███╗███████║██╔████╔██║█████╗      ██║   ██║██║   ██║█████╗  ██████╔╝
                    ██║   ██║██╔══██║██║╚██╔╝██║██╔══╝      ██║   ██║╚██╗ ██╔╝██╔══╝  ██╔══██╗
                    ╚██████╔╝██║  ██║██║ ╚═╝ ██║███████╗    ╚██████╔╝ ╚████╔╝ ███████╗██║  ██║
                    ╚═════╝ ╚═╝  ╚═╝╚═╝     ╚═╝╚══════╝     ╚═════╝   ╚═══╝  ╚══════╝╚═╝  ╚═╝
                
                ----------------------------------------------------------------------------------
                        """
                );
                timer.sleep(1000);

                System.out.println("########################################");
                System.out.println("#######         추리 실패......           ");
                System.out.println("########################################");
                timer.sleep(1000);

                System.out.println("\n" + murderer.getName() + ": 명탐정 별거 없네~~~ 해커톤 우승은 내꺼다!!!");
                timer.sleep(1000);

                System.out.println("\n안타깝습니다... 범인은 " + murderer.getName() + "씨였습니다. 모든 기회를 다 써버렸습니다.");
                timer.sleep(1000);
                System.out.println("추리는 실패했습니다. " + detectiveName + " 탐정님, 이번 사건은 당신에게 큰 상처로 남게 될 것입니다.");
                timer.sleep(1000);
                System.out.println("당신은 이 미스터리를 풀 기회를 잃었습니다...");
                timer.sleep(1000);
                System.out.println("하지만, 진정한 탐정은 실패에서도 배웁니다. 다음엔 꼭 진실을 밝혀내세요.");
                timer.sleep(1000);

                askRestart();
            }
        }
    }

    public void askRestart() {
        String choice = promptChoice("게임을 다시 시작하시겠습니까? (네/아니오): ");
        if (choice.equals("네")) {
            resetGame();
            play();
        } else {
            System.out.println("게임을 종료합니다. 감사합니다!");
        }
    }

    public void resetGame() {
        lives = 2;
        victim = null;
        murderer = null;
        suspect = null;
    }

    public void mainFlow() {
        while (true) {
            investigate();
            if (promptChoice("\n계속 조사하시겠습니까? (네/아니오): ").equals("아니오")) {
                break;
            }
        }
        accuse();
    }

    public void play() {
        init();
        showIntro();
        mainFlow();
    }
}
