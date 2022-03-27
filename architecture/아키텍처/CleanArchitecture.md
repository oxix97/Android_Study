# Clean Architecture

---

### Clean Architecture?

애플리케이션은 새로운 기능이 추가된다거나 내부 로직이 변경되어야 하는 일이 생겼을 경우 유연하게 대처할 수 있도록 구조화해야한다. -> 유지 보수하기 쉽게 구조가 구성되어야 한다. 클린 아키텍처의 목표는 계층을 분리하여 관심사를 분리하는 것이다. 관심사를 분리한다는 것이 어떤 의미 일까??

### 예시

기존에 수많은 기능과 관련이 되어 있는 Realm을 Room으로 교체해야 한다는 상황이 왔을 때 프로젝트의 복잡도가 높을 수록 바꾸기에는 쉽지 않다. 이러한 경우 클린 아키텍처로 프로젝트 구조를 잡는 다면 변화에 유연하게 코드를 작성할 수있다.

---

### 클린 아키텍처의 계층

클린 아키텍처는 4가지의 계층으로 이루어져 있다. 계층을 나누는 이유는 계층을 분리하여 관심사를 분리시키기 위해서이며 이런 아키텍처가 동작하기 위해서는 의존성 규칙을 지켜야 한다. -> 각 분리된 클래스가 한가지 역할만 하고 서로 의존을 어떻게 할지 규칙이 정해져있어 지켜야한다.

#### 의존성?

예를 들어 서비스로 사용할 수 있는 객체이다. 클라이언트가 어떤 서비스를 사용할 것인지 지정하는 대신, 클라이언트에게 무슨 서비스를 사용할 것인지 말해주는 것이다. 주입이라는 단어는 의존성(서비스)를 사용하려는 객체로 전달하는 것을 의미한다.

#### 의존성규칙?

의존성 규칙은 모든 소스코드 의존성은 반드시 외부에서 내부로, 고수준 정책을 향해야 한다. 안드로이드로 예로들면 비즈니스 로직을 담당하는 ViewModel과 같은 코드들이 DB 같은 구체적인 세부사항에 의존하지 않아야 한다. 비즈니스로직(고수준)은 세부사항(저수준)의 변경에 영향을 받지 않도록 해야 한다.

#### 이점

- 코트 테스트커버리지 증대
- 쉽게 패키지 구조 탐색 가능
- 새 기능을 빠르게 적용
- 집중화된 클래스에 따른 프로젝트 유지 관리 증대
- 명확한 규율로 전반적으로 따라야 할 베스트 프랙티스 -> 유지보수 , 협업에 좋음

### 클린아키텍처 계층

![image-20220317022307306](/Users/chan/Library/Application Support/typora-user-images/image-20220317022307306.png)



<b> 1.  Entities </b>

엔티티는 비즈니스 규칙을 캡슐화한다. 엔티티는 메서드를 갖는 갖객체일 수도 있지만 데이터 구조와 함수의 집합일 수도 있다. 가장 일반적이면서 고수준의 규칙을 캡슐화 한다. -> 외부가 변경되더라도 이러한 규칙이 변경될 가능성은 적어진다.

<b>2. Usecases</b>

유스케이스는 애플리케이션의 고유 규칙을 캡슐화하며 엔티티로부터의 데이터 흐름을 조합한다. 유스케이스 계층의 변경이 엔티티에 영향을 주어서는 안되며 DB, 공통 프레임워크 및 UI 에 대한 변경으로부터 격리된다.

<b>3. Interface Adapters</b>

인터페이스 어댑터는 엔티티 및 유스케이스의 편리한 형식에서 데이터베이스 및 웹에 적용할 수 있는 형식으로 변환한다. 해당 계층에는 MVP패턴의 Presenter, MVVM패턴의 ViewModel이 포함된다. -> 비즈니스 로직만을 담당하는 역할

<b>4. Frameworks & Drivers</b>

프레임워크와 드라이버는상세한 정보들을 두게 된다. 웹 프레임워크, 데이터베이스, UI, HTTP client등 구성된 가장 바깥쪽 계층이다.

---

### 안드로이드에서의 클린 아키텍처

안드로이드에서는 대부분 Entity레이어 나누지 않고 Controller(Interface adapter)등 직접적으로 접하지 않는 용어 위의 설명에서 사용된다. 또한 가장 바깥계층인 Frameworks & Drivers에 DB, Web과 함께 UI도 포함되어 있어 혼란이 올 수 있다. 안드로이드용으로 이해하기 쉽게 만들어진 클린 아키텍처 구조는 Entity레이어를 따로 두지않고 일반적으로 Presentation, Domain, Data 3개의 계층으로 나뉘게 된다.  안드로이드에 맞춘 클린아키텍처 구조는 다음과 같다.

![image-20220317023533538](/Users/chan/Library/Application Support/typora-user-images/image-20220317023533538.png)

![image-20220317023713496](/Users/chan/Library/Application Support/typora-user-images/image-20220317023713496.png)

---

### 계층

<b> 1. Presentation </b>

UI, Presenter 및 ViewModel을 포함한다. 즉 화면과 입력에 대한 처리등 UI와 직접적으로 관련된 부분을 담당한다. 또한 Presentation레이어는 Domain과 Data레이어를 포함하고 있다는 특징이 있고 Domain계층에 대한 의존성을 가지고 있다. Presentation -> Domain

<b> 2. Domain </b>

비즈니스 로직을 포함하고 비즈니스 로직에 필요한 Model, Usecase를 포함하고 있다. Domain레이어는 Presentation, Data레이어와 어떤 의존성도 맺지 않고 독립적이라는 특징이 있다.

<b> 3. Data</b>

Repository 구현체, Cache, Room, Dao, Model, API를 포함하고 있으며 로컬 또는 서버 API와 통신하여 데이터를 CRUD하는 역할을 한다. 또한 Mapper클래스도 포함하고 있는데 DB로 부터 받아온 데이터 모델과 UI에맞는 데이터 모델간의 변환을 해주는 역할을 한다. Domain 레이어를 포함하고 있다는 특징이 있고 Domain계층에 의존성을 가지고 있다.



### 참조

- [클린 아키텍처 참조 문서1](https://medium.com/@justfaceit/clean-architecture%EB%8A%94-%EB%AA%A8%EB%B0%94%EC%9D%BC-%EA%B0%9C%EB%B0%9C%EC%9D%84-%EC%96%B4%EB%96%BB%EA%B2%8C-%EB%8F%84%EC%99%80%EC%A3%BC%EB%8A%94%EA%B0%80-1-%EA%B2%BD%EA%B3%84%EC%84%A0-%EA%B3%84%EC%B8%B5%EC%9D%84-%EC%A0%95%EC%9D%98%ED%95%B4%EC%A4%80%EB%8B%A4-b77496744616)
- [클린 아키텍처 참조 문서2](https://youngest-programming.tistory.com/484)
- [클린 아키텍처 참조 문서3](https://leveloper.tistory.com/205)

[클린 아키텍처 image](https://medium.com/@justfaceit/clean-architecture%EB%8A%94-%EB%AA%A8%EB%B0%94%EC%9D%BC-%EA%B0%9C%EB%B0%9C%EC%9D%84-%EC%96%B4%EB%96%BB%EA%B2%8C-%EB%8F%84%EC%99%80%EC%A3%BC%EB%8A%94%EA%B0%80-1-%EA%B2%BD%EA%B3%84%EC%84%A0-%EA%B3%84%EC%B8%B5%EC%9D%84-%EC%A0%95%EC%9D%98%ED%95%B4%EC%A4%80%EB%8B%A4-b77496744616) 