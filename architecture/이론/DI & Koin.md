# DI & Koin

---

#### DI(의존성 주입)

- DI는 일반적으로 클래스 내부에서 객체를 생성하지 않고 외부에서 객체를 생성해 주입 받는 방식이다.

#### 사용하는 이유?

<b>DI 적용 전</b>

~~~kotlin
class Manager { ... }

class DodoCart {
    val manager = Manager()
    ...
}

class DodoPoint {
    val manager = Manager()
    ...
}

fun main() {
    val dodoCart = DodoCart()
    val dodoPoint = DodoPoint()
}
~~~

예시 코드에서 DodoCart, DodoPoint 클래스는 Manager 의존성을 가지고 있다. 해당 코드에는 몇 가지 문제가 발생할 수 있다. Manager클래스에 변화가 생길 경우 해당 의존성을 갖는 모든 클래스가 변경되어야 한다. 프로젝트가 크면 클 수록 변경해야 하는 상황이 많아질 것이다. 테스트 역시 마찬가지이다.

##### DI 적용 후

~~~kotlin
class Manager { ... }

class DodoCart(val manager: Manager) { ... }

class DodoPoint(val manager: Manager) { ... }

fun main() {
    val manager = Manager()
    val dodoCart = DodoCart(manager)
    val dodoPoint = DodoPoint(manager)
}
~~~

main 함수에서 Manager 객체를 생성하고 생성자를 통해 각 클래스에 의존성을 주입해주었다. Manager 객체는 재사용이 가능해지고, 모의 객체를 만들고 단위 테스트로 분리하기 쉬워졌다.

#### 장점

1. 코드의 가독성과 재사용성을 높여준다.
2. 단위 테스트하기 편하다
3. 객체 간의 의존관계를 직접 설정할 수 있다.
4. 객체 간의 결합도를 낮출 수 있다.

---

### 안드로이드에서의 DI

특히 안드로이드에서는 어떤 Activity, Fragment에서 객체를 생성하는지에 따라 context가 게속 바뀌기 때문에 가은 클래스 타입 객체임에도 다르게 동작 할 수 있다.하지만 범용된 환경에서 객체를 생성하고 이렇게 생성된 객체를 Activity, Fragment에서 주입 받아 사용하는 식으로 구현하면 context의 영향을 받지 않고도 공통으로 재사용할 수 있는 객체를 구현하게 된다.

안드로이드에서는 두가지 방식의 의존성 주입이 가능하다.

1. 생성자 주입 : 생성자를 통해 의존하는 객체 전달
2. 필드 주입 : 객체가 초기화된 후에 의존하는 객체 전달

---

### Koin이란?

Koin은 Android에서 주로 사용되는 경량화된 의존성 주입용 프레임워크이다. 의존성 주입이란 객체를 생성하고 사용하는 관심사를 분리하는 것에 목적을 가지고 있다.

Koin을 사용할 경우 런타임에 의존성 주입을 하다보니 앱 성능이 저하된다는 단점이 있다. 따라서, 큰 규모의 프로젝트에서 Koin을 사용할 경우 Application이 시작될 때 의존성 그래프가 그려지다 보니 화면이 멈춘거처럼 될 수 있다. 따라서 큰 규모의 프로젝트에서는 컴파일 타임에 의존성 그래프를 그려주는 Dagger-Hilt을 이용해 의존성 주입을 하는 것이 좋다.

### 장점

- 러닝커브가 낮아 쉽고 빠르게 DI를 적용할 수 있습니다.
- Kotlin 개발 환경에 도입하기 쉽습니다.
- 별도의 어노테이션을 사용하지 않기 때문에 컴파일 시간이 단축됩니다.
- ViewModel 주입을 쉽게 할 수 있는 별도의 라이브러리를 제공합니다.

### 단점

- Dagger2와 달리 런타임에서 에러가 날 수도 있습니다(단위 테스트를 통해 방지할 수는 있습니다).
- Activity나 Fragment, Service 등이 아닌 곳에서 사용하기 위해선 생성자로 넘기거나 별도의 구현을 해야 합니다.

---

### 사용법

##### 모듈 선언

module 키워드로 주입 받고자 하는 객체를 모듈로 만들어 선언할 수 있다.

~~~kotlin
class SampleRepository() {
    val sampleData = "Sample Data!"
}

class SampleController(val repository: SampleRepository) {
    fun printSampleData() {
        Log.d("Print sample data", repository.sampleData)
    }
}

class SampleViewModel : ViewModel() {
    private var _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean> = _isLoading
}
~~~

module 키워드를 이용해 모듈로 선언하고 변수에 저장한다.

~~~kotlin
val appModule = module {
    single { SampleRepository() }
    factory { SampleController(get()) }
}
~~~

- single{ } : 앱이 실행되는 동안 계속 유지되는 싱글톤 객체를 생성한다.
- factory{ } : 요청할 때마다 매번 새로운 객체를 생성한다.
- get( ) : 컴포넌트 내에서 알맞은 의존성을 주입 받는다. 해당 예제에서는 SampleRepository 타입으로 선언되어 있으므로 이미 생성된 객체 중 SampleRepository 타입에 알맞은 객체를 Koin이 주입해준다.
- getProperty( ) : 필요한 프로퍼티를 가져온다.
- named( ) : Enum이나 String으로 한정자를 정의해준다.
- bind : 지정된 컴포넌트의 타입을 추가적으로 바인딩해준다.
- viewModel의 경우 viewModel 키워드로 선언해야 한다.

~~~kotlin
val viewModelModule = module {
    viewModel { SampleViewModel() }
}
~~~

---

#### 모듈 등록

onCreate LifeCycle에서 startKoin을 호출하고 위에서 선언한모듈 변수를 넘겨준다.

~~~kotlin
class SampleApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidLogger()
            androidContext(this@SampleApplication)
            modules(appModule)
            modules(viewModelModule)
        }
    }
}
~~~

- androidLogger( ) : AndroidLogger를 Koin logger로 사용한다.
- androidContext( ... ) : 해당 안드로이드 context를 사용한다.
- modules( ... ) : 사용할 모듈을 등록한다.

----

### 의존성 주입 받기

by inject( )로 Koin에 등록된 객체를 lazy하게 주입 받을 수 있다. 사용하고자하는 Activity클래스에서 주입받으면 된다.

~~~kotlin
class SampleActivity : AppCompatActivity() {

    val controller: SampleController by inject()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate()
    }
}
~~~

ViewModel의 경우 by viewModel(  )키워드를 사용한다.

~~~kotlin
val viewModel: SampleViewModel by viewModel()
~~~

---

#### TEST

- JUnit 테스트 클래스에서 KoinTest를 상속받아 Koin모듈을 주입 받을 수 있다.
- KoinTestRule을 통해 Koin context를 시작/중단 할 수 있다.

~~~kotlin
class SampleTest : KoinTest {

    @get:Rule
    val koinTestRule = KoinTestRule.create {
        modules(appModule)
    }

    val controller : SampleController by inject()

    @Test
    fun sampleTest() {
        ...
    }
}
~~~

