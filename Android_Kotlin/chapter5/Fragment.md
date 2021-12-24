# Fragment

---

### Fragment??

> 액티비티를 구성하다 보면 화면이 너무 복잡하거나 또는 코드의 양이 너무 많아졌거나 하는 이유로 화면을
> 부위별로 따로 동작시키고 싶을 때 각각 분할해서 독립적인 코드로 구성할 수 있게 도와주는 것이 Fragment다.

> 한 화면에 1개의 프래그먼트를 나타나게 하여 스와이프를 통해 화면 간 이동을 하거나,
> 한 번에 여러개의 프래그먼트가 동시에 나타나는 형태로 대형 화면을 가진 디바이스에서 나타낼 경우 사용된다.

#### !! 화면이 하나만 필요한 경우에는 프레그먼트를 사용하지 않는다.

---

#### onCreateView의 파라미터

- inflater : 레이아웃 파일을 로드하기 위한 레이아웃 인플레이터를 기본을 제공한다.
- container : 프레그먼트 레이아웃이 배치되는 부모 레이아웃이다.
- savedInstanceState : 상태 값 저장을 위한 보조 도구, 액티비티의 onCreate의 파라미터와 동일하게 동작한다.

### 액티비티에 프래그먼트 추가하기

> 프래그먼트는 기본적으로 하나의 뷰로 동작하기 때문에 액티비티 안에 뷰를 삽입할 수 있는 레이아웃을 준비해야한다.
> 메인 액티비티 xml에 프래그먼트 영역을 설정하고 Activity 파일에 setFragment()함수를 만들어 호출하게 한다.

```kotlin
private fun setFragment() {
    val oneFragment: OneFragment = OneFragment()
    
    //프래그먼트 매니저를 통해 트랜잭션을 시작한다.
    val transaction = supportFragmentManager.beginTransaction()
    
    //add()메서드로 레이아웃에 ㅁ생성한 oneFragment를 삽입 
    transaction.add(R.id.fr_container, oneFragment)
    
    //commit() 메서드로 모든 작업이 정상적으로 처리되었음을 트랜잭션에 알려주면 작업이 반영된다.
    transaction.commit()
}
```

액티비티에 프래그먼트를 삽입하기 위해서는 프래그먼트 매니저를 통해 삽입할 레이아웃의 id를 지정해야한다.
프래그먼트를 삽입하는 과정은 하나의 트랜잭션으로 관리되기 때문에 트랜잭션 매니저를 통해 begin transaction > add fragment > commit transaction
순서로 처리된다.

---
### 프레그먼트 화면 전환














