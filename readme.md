# 📱 **프리코스 오픈 미션 — Kotlin 기반 안드로이드 가계부 앱**

---

## 📖 개요
**Kotlin Expense Tracker App**은 **Jetpack Compose 기반의 안드로이드 가계부 애플리케이션**으로,  
사용자가 입력한 수입·지출 내역을 저장하고 잔액 및 카테고리별 통계를 확인할 수 있는 모바일 앱입니다.

---

## 🎯 프로젝트 목표
- Jetpack Compose를 활용해 **현대적인 Android UI 개발 방식을 학습**한다.
- Kotlin의 **간결한 문법, null safety, sealed class, 데이터 클래스** 활용을 실전에서 경험한다.
- 프리코스의 **Clean Code·OOP·TDD 철학**을 모바일 앱 구조에서도 실습한다.
- **Room DB, ViewModel, State 관리**를 사용해 실제 동작하는 가계부 앱을 구현한다.
- 앱 구조 분리(리포지토리, UseCase, UI 계층)를 통해 **유지보수성과 확장성 높은 프로젝트**를 경험한다.

---

## 🧾 주요 기능

| 번호 | 기능명 | 설명 |
|------|---------|------|
| **1** | **수입 추가 (Add Income)** | 날짜, 금액, 카테고리, 메모를 입력받아 앱에 수입 내역 저장 |
| **2** | **지출 추가 (Add Expense)** | 날짜, 금액, 카테고리, 메모를 저장하고 리스트에 반영 |
| **3** | **전체 내역 보기 (View Transactions)** | Compose List UI로 날짜순 거래내역 표시 |
| **4** | **카테고리별 통계 (Statistics)** | 카테고리·기간별 지출/수입 비율 시각화 |
| **5** | **잔액 보기 (Balance Check)** | 총 수입 – 총 지출 계산하여 잔액 표시 |

---

## 🧩 기술 스택 (Tech Stack)

### **Android**
- Language: **Kotlin**
- UI Framework: **Jetpack Compose (Material3)**
- Architecture: **MVVM + Repository Pattern**
- Persistence: **Room Database**
- Coroutine: 비동기 처리
- Navigation: Navigation Compose
- Jetpack Libraries: ViewModel, LiveData/StateFlow, Lifecycle

---

## ⚠️ 예외 처리 (모바일 환경 기준)

| 구분 | 상황 | 처리 방식 | 결과 |
|------|------|------------|------|
| **입력값 오류** | 금액이 음수·숫자 아님 | TextField 검증 + SnackBar | 잘못된 입력 안내 |
| **날짜 오류** | 잘못된 형식 | DatePicker 사용 | 날짜 형식 보장 |
| **비어있는 필드** | 메모/카테고리 미입력 | UI Validation | UX 개선 |
| **DB 오류** | Room 접근 실패 | try-catch + Toast | 앱 비정상 종료 방지 |
| **Unknown 오류** | 예상치 못한 오류 | Error UI 상태 표시 | 안정성 향상 |

---

## 📄 라이선스
MIT License © 2025 DoYoung


