# Activity

---

- Context
- Intent
- 생명주기
---
### Context

> 시스템을 사용하기 위한 정보와 도구가 담겨 있는 클래스이다. 대부분의 컨텍스트는 컴포넌트 실행시에 함께 생성되고, 생성된 컴포넌트가 가지고 있는 메서드를 호출해서 각각의 도구들을 사용할 수 있다. 안드로이드의 컨텍스> 트는 앱을 실행하기 위해 잘 짜여진 기본 기능이 담겨 있는 기본 클래스이다. 액티비티는 컨텍스트를 상속받아 구현된다.

- startActivity() : 액티비티 실행
- startService() : 서비스 실행
- openFileInput() : 파일 읽기
- openFileOutput() : 파일 쓰기
- checkSelfPermission() : 권한

---
#### 1. 애플리케이션 컨텍스트

> 애플리케이션과 관련된 핵심 기능을 담고 있는 클래스, 앱을 통틀어서 하나의 인스턴스만 생성된다.
> 액티비티나 서비스 같은 컴포넌트에서 applicationContext를 직접 호출해서 사용할 수 있는데
> 호출하는 지점과 관계없이 모두 동일한 컨텍스트가 호출된다.

#### 2. 베이스 컨텍스트

> 안드로이드의 4대 메이저 컴포넌트인 액티비티, 서비스, 컨텍트 프로바이더, 브로드캐스트 리시버의 기반 클래스이다.
> 각각의 컴포넌트에서 baseContext , this로 컨텍스트를 사용할 수 있고 컴포넌트의 개수만큼 컨텍스트도 
> 함께 생성되기 때문에 호출되는 짖머에 따라 서로 다른 컨텍스트가 호출된다.

---
### Intent 과정

1. 실행할 대상의 액티비티 이름과 전달할 데이터를 담아 인텐트 생성
2. 생성한 인텐트를 startActivity() 메서드에 담아 호출하면 액티비티 매니저에 전달
3. 액티비티 매니저는 인텐트를 분석해서 지정한 액티비티 실행
4. 전달된 인텐트는 최종 목적지인 타깃 액티비티까지 전달  -> putExtra()
5. 타깃 액티비티에서는 전달받은 인텐트에 데이터가 있다면 이를 꺼내서 사용할 수 있다.  -> getExtra()
6. 값을 돌려 받는 경우 상태값을 setResult() 메서드에 담아 호출한 측으로 전달한 이후 finish()로 서브 액티비티를 종료한다. -> onActivityResult() : resultCode가 정상인지 확인한 이후 데이터를 받을 수 있다.

---

#### startActivityForResult()

- startActivity()와는 다르게 두번째 파라미터를 적을 수 있으며 requestCode를 onActivityResult()에서 구분할 수있다.

---
### 액티비티 생명 주기

|호출되는 메서드|액티비티 상태|설명|
|---|---|---|
|onCreate()|만들어짐|액티비티가 생성된다. 실제 코드를 가장 많이 작성하는 메서드|
|onStart()|화면에 나타남|화면에 보이기 시작한다.|
|onResume()|화면에 나타남|실제 액티비티가 실행되고 있다.|
| |현재 실행중|실행중은 생명 주기 메서드가 따로 없고, onResume이 호출되었다면 실행중이라는 의미이다.
|onPause()|화면이 가려짐|액티비티 화면의 일부가 다른 액티비티에 가려진다.|
|onStop()|화면이 없어짐|다른 액티비티가 실행되어 화면이 완전히 가려진다.|
|onDestroy()|종료됨|종료|


