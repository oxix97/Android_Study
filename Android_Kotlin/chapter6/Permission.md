# 권한

- 권한 명세 : 해당 데이터의 기능의 사용 여부를 설정
- 기능 명세 : 해당 기능이 있는 안드로이드 폰에서만 내려받을 수 있도록 플레이 스토어에서 내려받는 것을 방지

---

### 권한명세

AndroidManifest.xml에 있으며 `<use-permission/>` 태그를 포함하여 필요한 권한을 명세한다.

### 기능 명세

기능명세와는 달리 AndroidManifest.xml 파일에 따로 추가하지 않아도 해당 기능을 사용할 때 시스템이 자동으로 부여한다. 이때 사용하는 태그는 `<use-feature/>`라는 태그이며 직접 사용하려는 기능을 명세할 수도 있다.

---

## 권한의 보호 수준

- 일반 권한, 위험 권한, 서명 권한 세가지의 보호 수준으로 나뉘며 보호 수준에 따라 앱을 실행할 때 해당 권한에 대해 사용자에게 확인 요청이 필요하다.

### 일반 권한

AndroidManifest.xml에 명세하면 설치 시 사용사에게 권한 승인을 묻는 팝업창을 보여주며 인터넷 사용, 알람 설정 등 일반 권한에 포함된다. `<use-permission/>` 태그를 사용하여 권한을 입력하고 permission. 다음에 필요한 권한을 적으면 된다.

### 위험 권한

위험 권한은 앱이 사용자의 개인정보와 관련된 데이터나 기능을 엑세스하거나 다른 앱 및 기기의 작동에 영향을 줄 우려가 있는 권한이다. Gradle Scripts 디렉터리에 build.gradle 파일의 targetSdkVersion 23 부터는 AndroidManifest.xml에 권한을 명세하고 부가적으로 소스 코드에 권한 요청 및 처리 로직을 작성해야 한다.

### 서명 권한

서명 권한은 권한을 사용하려는 앱이 권한을 정의하는 앱과 동일한 인증서로 서명된 경우 시스템은 권한을 자동으로 부여한다.

---

## 위험한 권한 처리

### 단계

1. 사용 권한 정의 -> AndroidManifest.xml에 명세
2. 권한을 요청하는 xml 만들기
3. 소스 코드에서 위험 권한 처리
    1. 권한에 대한 사용자 승인 확인 (이전에 승인 처리를 했는지)
        - 설정 파일에서 명세한 카메라 권한에 대해 승인 처리가 되었는지를 확인
    2. 사용자에게 승인 요청
    3. 사용자 승인 후 처리

```kotlin
class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)

        binding.btnCameraAccess.setOnClickListener {
            checkPermission()
        }

        setContentView(binding.root)
    }

    private fun checkPermission() {
        val cameraPermission =
            ContextCompat.checkSelfPermission(this, android.Manifest.permission.CAMERA)
        if (cameraPermission == PackageManager.PERMISSION_GRANTED) {
            startProcess()
        } else {
            requestPermission()
        }
    }

    private fun requestPermission() {
        ActivityCompat.requestPermissions(this, arrayOf(android.Manifest.permission.CAMERA), 99)

    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        when (requestCode) {
            99 -> {
                if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    startProcess()
                } else {
                    finish()
                }
            }
        }
    }

    private fun startProcess() {
        Toast.makeText(this, "승인", Toast.LENGTH_SHORT).show()
    }

}
```





