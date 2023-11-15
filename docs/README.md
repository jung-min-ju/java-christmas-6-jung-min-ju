## 디렉터리 구조
```

christmas
   ├─Constant
   ├─Controller
   ├─Discount
   ├─IO
   ├─Menu
   ├─Order
   └─Util

```

---

## Constant 디렉터리
* DateConstant : 날짜를 나타내는 상수들의 집합 클래스인
* DiscountConstant : 할인을 나타내는 상수들의 집합 클래스
* GiftConstant 증정품에 대한 정보를 담은 상수들의 집합 클래스
* MenuConstant 메뉴에 대한 정보를 담은 상수들의 집합 클래스
* ValidateConstant 예외처리 시 출력문을 담은 상수들의 집합 클래스
* WeekConstant 평일과 주말에 대한 정보를 담은 상수들의 집합 클래스

---
## Controller 디렉터리
* Controller : 클라이언트 역할을 해줄 클래스.
```
* Reservation() : 사용자의 예약을 도와줌
* ShowMenuAndTotalPrice : 사용자에게 입력된 메뉴와 총 가격을 보여줌
* ShowBenefits() : 사용자에게 혜택 내용을 보여줌
* DetermineBedge() : 사용자의 총 할인가에 따른 배지정보 출력
```

---

## Discount 디렉터리


: 할인에 대한 모든 서비스 계층 클래스, 할인과 관련된 Enum 클래스 포함

* Badge : 필드에는 배지의 종류가, 총할인가격에 따라 배지를 반환해주는 enum 클래스.
* DiscountService : 할인에 대한 비즈니스 로직을 가지는 인터페이스
* DiscountServiceImpl : DiscountService 인터페이스를 상속받아 비즈니스 로직 구현
* StarStatus : 들어오는 입력값(날짜)가 별이 있는 날인지 없는 날인지 판단할 정보를 가진 Enum 클래스
* WeekType : 들어오는 입력값이 주말인지, 평일인지를 판단할 정보를 가진 Enum 클래스

---

## Gift 디렉터리
* GiftService : 증정품에 대한 비즈니스 로직을 가지는 인터페이스
* GiftService : GiftService 인터페이스를 상속받아 비즈니스 로직 구현

---

## IO 디렉터리
* InputView : 입력에 대한 로직 처리
* OutputView : 출력에 대한 로직 처리

---
## Menu 디렉터리
* MenuDto : 메뉴 정보(메뉴이름, 메뉴가격, 메뉴타입)을 담을 DTO
* MenuRepository : 메뉴를 저장할 저장소에 대한 로직을 가질 인터페이스
* MenuRepositoryImpl : MenuRepository를 상속받아 메뉴를 저장(C), 사용자 입력 메뉴가 있는지 확인(R) 기능 제공 
* MenuType : 메뉴의 타입(main, dessert, drink, epitizer)에 대한 정보를 담은 enum 클래스

---
## Order 디렉터리
* OrderDto : 주문정보(메뉴,수량)에 대한 정보를 담은 DTO
* OrderService : 주문에 대한 비즈니스 로직을 담은 인터페이스
* OrderServiceImpl : OrderService를 상속받아 주문정보를 저장(C), 주문 정보 조회(R), 총 주문가격 조회(R), 할인대상주문조회(R) 등의 비즈니스 로직 포함

---
## Util 디렉터리
* Formatter : 입력받은 정수형 가격을 콤마가 포함된 문자열 형태로 포맷팅하여 반환하는 기능을 수행
* Judge : 주말/주중인지 판단, 별이 있는지 없는지 판단하는 함수가 있음
* ParsingMenu : 사용자가 입력한 문자열에 속한 구분자 제거
* ParsingWeekDescription : 깔끔한 출력을 위하여 WeekType의 Description에서 중요 정보만 추출하도록 도와줌
* Validate : 유효성을 검사해주는 클래스