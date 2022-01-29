## 저장소

### 저장소 종류와 권한

안드로이드는 리눅스 기반의 파일 시스템으로 구성되어 있다. 리눅스 파일 시스템의 특징 중 하나는 파일과 디렉터리에 대한 권한 설정인데, 설치된 앱 하나당 리눅스 사용자 아이디와 그에 해당하는 디렉터리가 할당되며 각각의 디렉터리는 해당 사용자만 접근이 가능하다. 특정 앱의 사용자가 접근할 수 있는 영역을 내부 저장소, 모든 앱이 공용으로 사용할 수 있는 영역을 외부 저장소라고 한다.

---

### 내부 저장소

내부 저장소는 설치한 앱에 제공되는 디렉터리이다. 내부 저장소에는 주로 내 앱에서만 사용하는 데이터를 저장하며 다른 앱이 공유할 필요가 없으므로 데이터를 내부 저장소에 저장하는 것이 좋다.

### 외부 저장소

외부 저장소는 모든 앱이 함께 사용할 수 있는 공간이다. 외부 저장소에 저장된 파일에 접근하려면 앱의 매니페스트에 접근하려는 파일은 물론 외부 저장소 디렉터리에 권한을 명세해야 한다.

---

## SharedPreferences

안드로이드 플랫폼은 간단한 데이터의 저장을 목적으로 SharedPreferences를 제공한다. 외부 저장소에 저장할 때 권한 설정이 필요한 반면, SharedPreferences는 내부 저장소를 이용하기 때문에 권한 설정이 필요 없고 훨씬 간단한 코드로 사용할 수 있다. 주로 로그인 정보나 앱의 상태 정보를 저장하는 용도로 사용되며 액티비티에서 인텐트에 값을 넣고 빼는 것과 비슷한 형태로 동작한다. SharedPreferences는 인텐트에 값을 전달하듯이 데이터를 키와 깞 쌍으로 저장이 가능하며 데이터는 XML 형식으로 된 파일로 저장되고 앱이 종료되어도 남아있다.

### SharedPreferences를 사용하기

#### 저장

1. SharedPreferences 생성하기
2. Editor 꺼내기
3. putString(), putInt() 메서드로 저장하기
4. apply()로 파일에 반영하기

#### 읽기

1. SharedPreferences 생성하기
2. getInt(), getString() 메서드로 값 읽어오기

### getSharedPreferences()

getSharedPreferences()는 Context를 가지고 있는 모든 컴포넌트에서 접근과 호출이 가능하다. getSharedPreferences("이름","모드")를 액티비티에서 호출하면 SharedPreferences가 반환된다.

### getPreferences()

개별 액티비티에서 사용하거나 액티비티가 하나밖에 없는 앱이라면 getPreferences()를 호출해서 사용할 수 있다. 호출하는 액티비티의 이름으로 사용할 수 있다.

---

### Editor

SharedPreferences로 데이터를 저장하기 위해서는 Editor인터페이스를 사용해야하며 edit()메서드를 호출해서 사용 가능하다.

```kotlin
val shared = getSharedPreferences("이름",Context.MODE_PRIVATE)
val editor = shared.edit()
editor.putString("key","value")
editor.apply()
```

반면 데이터를 불러올때는 저장할 때와는 다르게 Editor를 사용하는 단계가 없으며, SharedPreferences의 메서드를 직접 호출해 데이터를 불러온다. defaultValue를 지정하면 해당 키의 데이터가 없으면 지정한 기본값을 반환한다.

그 외에도 Editor를 사용해서 삭제처리가 가능하며 이후에 apply()를 호출해야 완료 된다.