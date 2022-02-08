# 데이터베이스

### 쿼리

관계형 데이터베이스는 SQL이라는 데이터를 정의, 조작, 제어하는 용도의 언어로 사용한다. 이때 사용하는 명령어를 쿼리라고 하며 생성과 관련된 쿼리를 제외하면 테이블의 내용을 수정
삭제 하는 명령어이다.

### 쿼리의 종류

### DDL

- 데이터의 구조를 정의하는 명령어이며 테이블을 생성하고 컬럼의 속성을 정의하는 일이 포함된다.

| SQL          | 설명                                                         |
| ------------ | ------------------------------------------------------------ |
| CREATE TABLE | 테이블 생성<br />CREATE TABLE 테이블명                       |
| DROP TABLE   | 테이블 삭제<br />DROP TABLE 테이블명                         |
| ALTER TABLE  | 테이블 수정 (컬럼 수정, 추가, 삭제)<br >ALTER TABLE 테이블명 (ADD/DROP/MODIFY) COLUMN 컬럼 타입 |

---

### DML

- 데이터를 조작하는 명령어이며 가장 많이 사용되는 명령어이다. CRUD에 해당하는 네가지 명령어에 대한 이해가 필요하다.

| SQL    | 명령   | 설명                                                         |
| ------ | ------ | ------------------------------------------------------------ |
| SELECT | Read   | 데이터 조회<br />SELECT 컬럼 FROM 테이블명 WHERE 조건        |
| INSERT | Create | 데이터 삽입<br />INSERT INTO 테이블명 VALUES (데이터)        |
| UPDATE | Update | 데이터 수정<br />UPDATE 테이블명 SET 컬럼 = 데이터 WHERE 조건 |
| DELETE | Delete | 데이터 삭제<br />DELETE FROM 테이블명 WHERE 조건             |

---

### DCL

- 데이터 조작 명령어 -> 데이터베이스 권한과 관련된 명령어이며 주로 특정 유저에게 읽기와 쓰기 권한을 부여할 때 주로 사용한다.

---

## SQLite 데이터 베이스

안드로이드 기본 데이터베이스는 경량 데이터베이스인 SQLite이다. 안드로이드 앱에서 사용할 삽입, 조회, 수정, 삭제와 관련된 기능에 한정해서만 알아보도록 한다.

컬럼은 컬럼명, 타입, 옵션 순서대로 작성하고 공백으로 구분한다. 컬럼이 2개 이상인 경우는 쉼표로 컬럼을 구분하며 컬럼에 따라 옵션이 없는 경우도 있다.

~~~kotln
CREATE TABLE memo(
		no INTEGER PRIMARY KEY,
		content TEXT,
		datetime INTEGER
)
~~~

쿼리를 작성할 때 일정한 규칙( 모두 대문자, 모두 소문자 )만 있다면 모두 소문자 또는 대문자로 작성해도 상관없다.

---

### SQLiteOpenHelper

SQLiteOpenHelper 클래스는 데이터베이스를 파일로 생성하고 코틀린 코드에서 사용할 수 있도록 데이터베이스와 연결하는 역할을 한다.

#### onCreate

데이터베이스가 생성되지 않으면 onCreate에서 테이블을 생성한다. 해당 메서드 안에 테이블 생성 쿼리를 작성하고 실행하면 된다. 데이터베이스가 생성되어 있으면 더 이상 실행되지
않는다.

### onUpgrade

SqliteHelper에 전달되는 버전 정보가 변경되었을 경우 현재 생성되어 있는 데이터베이스 버전과 비교하여 더 높은 경우 호출된다. 버전 변경사항이 없으면 호출되지 않는다.

~~~kotlin
class SqliteHelper(context: Context, name: String, version: Int) :
    SQLiteOpenHelper(context, name, null, version) {
    override fun onCreate(db: SQLiteDatabase?) {
        val create = "create table memo (" +
                "id integer primary key, " +
                "content text, " +
                "datetime integer" + ")"
        db?.execSQL(create)
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {

    }
}
~~~

---

### 삽입 메서드

SQLiteOpenHelper를 이용해서 값을 입력할 떄는 코틀린의 Map클래스처럼 키, 값 형태로 사용되는 ContentValues 클래스를 사용한다. Contentvalues에
put 을 사용하여 저장한다.

~~~kotlin
private fun insertMemo(memo: Memo) {
    val values = ContentValues()
    values.put("content", memo.content)
    values.put("datetime", memo.datetime)

    val wd = writableDatabase
    wd.insert("memo", null, values)
    wd.close()
}
~~~

상속받은 SQLiteOpenHelper에 이미 구현된 writableDatabase에 테이블명과 함께 앞에서 작성한 값을 전달해 insert( )하고 이후 꼭 close( )
호출해서 닫아줘야한다.

---

### 조회 메서드

#### Cursor

데이터셋을 처리할 때 현재 위치를 포함하는 데이터 요소입니다. 커서를 사용하면 쿼리를 통해 반환된 데이터셋을 반복문으로 반복하여 하나씩 처리할 수 있습니다. 반복할 때마다 현재
위치를 가리키고 있어 단순 로직으로 데이터를 쉽게 처리할 수 있다.

~~~kotlin
private fun selectMemo(): MutableList<Memo> {
    val list = mutableListOf<Memo>()
    val select = "select * from memo" // 메모의 전체 데이터 조회하는 쿼리
    val rd = readableDatabase // 읽기 전용 데이터베이스를 변수에 담는다.

    // 데이터베이스의 rawQuery()메서드에 앞에 작성한 쿼리를 담아 실행 -> cursor 형태로  반환
    val cursor = rd.rawQuery(select, null)

    while (cursor.moveToNext()) { // moveToNext()가 다음 줄에 사용할 수있는 레코드가 있는지 여부를 반환 없으면 반복문 종료
        val num = cursor.getLong(cursor.getColumnIndex("num"))
        val content = cursor.getString(cursor.getColumnIndex("content"))
        val datetime = cursor.getLong(cursor.getColumnIndex("datetime"))
        list.add(Memo(num, content, datetime))
    }
    // 메모리 누수를 막기위한 작업
    cursor.close()
    rd.close()
    return list
}
~~~

---

### 수정 메서드

INSERT와 동일하게 ContentValues를 사용하여 수정할 값을 저장한다 update 메서드를 사용하여 수정한다. update 메서드의 파라미터는 총 4개인데 테이블명,
수정할 값, 수정할 조건순서이다.

~~~kotlin
private fun updateMemo(memo: Memo) {
    val values = ContentValues()
    values.put("content", memo.content)
    values.put("datetime", memo.datetime)

    val wd = writableDatabase
    wd.update("memo", values, "num = ${memo.num}", null)
    wd.close()
}
~~~

---

### 삭제 메서드

삭제 쿼리 : DELETE FROM 테이블명 WHERE 조건식

~~~kotlin
private fun deleteMemo(memo: Memo) {
    val delete = "delete from memo where num = ${memo.num}"

    val db = writableDatabase
    db.execSQL(delete)
    db.close()
}
~~~

---