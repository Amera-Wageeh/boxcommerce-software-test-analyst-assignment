# BoxCommerce – Manual Test Cases (Comprehensive & Consistent)

This suite covers **merchant sign‑up, onboarding, domain/EFT enablement, and Usersite checkout with EFT**.  
Scenarios include **positive, negative, boundary, decision‑table, and pairwise** techniques.  

---

## 1) Getting Started – Account Creation

### TC-GS-001 – Sign Up with Valid Data and Coupon
**Preconditions**: Access sign-up page.  
**Data**: Unique email, strong password, UAE phone, coupon `UATQA-DEMO`.  
**Steps**: Fill fields → accept terms → submit.  
**Expected**: Redirect to `/welcome`; account created; welcome/onboarding entry point visible.

### TC-GS-002 – Sign Up with Invalid Coupon
**Data**: Coupon `INVALID123`.  
**Expected**: Clear error; no account creation; form remains editable.

### TC-GS-003 – Phone Number Validation (UAE Format)
**Data / Steps**: Try: `12345` (short), `12345678901` (long), `abcdefghi` (non‑numeric), `501234567` (valid).  
**Expected**: First three rejected with specific messages; last accepted.

### TC-GS-004 – Weak Password Policy
**Data**: `12345678`, `password`, `Qwerty123`.  
**Expected**: Policy message lists unmet criteria; submit disabled or blocked.

### TC-GS-005 – Duplicate Email
**Precondition**: Existing account for `X@example.com`.  
**Data**: Reuse `X@example.com`.  
**Expected**: Duplicate email error; no new account.

### TC-GS-006 – Terms Not Accepted
**Steps**: Leave terms unchecked → submit.  
**Expected**: Must‑accept validation shown.

### TC-GS-007 – Mandatory Fields Empty
**Steps**: Submit with one or more required fields blank.  
**Expected**: Field‑level errors; focus to first invalid field.

### TC-GS-008 – Rate Limiting / Multiple Rapid Attempts
**Steps**: Trigger multiple submissions quickly.  
**Expected**: Graceful handling; no duplicate accounts; informative error if throttled.

---

## 2) Onboarding – Post‑Signup Setup

### TC-OB-001 – Complete Minimal Onboarding
**Steps**: Provide store name, basic profile; skip non‑mandatory steps if allowed.  
**Expected**: Land on dashboard; onboarding status updated.

### TC-OB-002 – Add a Category
**Data**: Category name (unique).  
**Expected**: Category appears in list; usable in product creation.

### TC-OB-003 – Add a Product (VAT Inclusive)
**Data**: Product A, price incl. VAT; stock > 0; assign category.  
**Expected**: Product created; visible in catalog.

### TC-OB-004 – Add a Product (VAT Exclusive)
**Data**: Product B, price excl. VAT; tax class 15%.  
**Expected**: Product created; tax class saved.

### TC-OB-005 – Add Click & Collect Point
**Expected**: Pickup point added; visible during checkout when C&C selected.

### TC-OB-006 – Product Without Mandatory Fields
**Steps**: Omit required fields.  
**Expected**: Inline validation; cannot save until corrected.

---

## 3) Domain & EFT Enablement

### TC-DM-001 – Retrieve Default Domain
**Steps**: Navigate Website → Domain(s); copy domain.  
**Expected**: Domain `ameratesting.uat.boxcommerce.dev` is visible and copyable.

### TC-DM-002 – Request EFT Enablement
**Steps**: Domain `ameratesting.uat.boxcommerce.dev` has been submitted and EFT enabled.  
**Expected**: EFT enabled for Usersite; confirmation received.

### TC-DM-003 – Verify EFT Checkout on Usersite
**Steps**:  
1. Open `http://ameratesting.uat.boxcommerce.dev/`  
2. Add a product → checkout → payment.  
3. Select EFT and complete.  
**Expected**: EFT payment completes successfully; order confirmation displayed.

---

## 4) Usersite Checkout – Decision Table

| Case | Tax | Voucher | Shipping | Customer | Expected |
|------|-----|---------|----------|----------|----------|
| DT1  | Inclusive | None    | Delivery | Guest | Subtotal includes tax; add delivery; EFT success. |
| DT2  | Inclusive | Fixed   | Delivery | Registered | Apply fixed voucher; add delivery; EFT success. |
| DT3  | Inclusive | Percent | C&C      | Registered | Apply % voucher; no shipping if policy; EFT success. |
| DT4  | Exclusive | None    | Delivery | Guest | Subtotal excl tax; add tax; add delivery; EFT success. |
| DT5  | Exclusive | Fixed   | C&C      | Registered | Fixed voucher; tax after discount; C&C; EFT success. |
| DT6  | Exclusive | Percent | Delivery | Registered | % voucher; tax after discount; delivery; EFT success. |

---

## 5) Checkout – Concrete Scenarios

### TC-CK-001 – Inclusive Tax, No Voucher, Delivery, Guest
**Steps**: Add product A (VAT incl) → checkout as guest → delivery → EFT.  
**Expected**: Totals = subtotal (incl tax) + shipping; EFT success.

### TC-CK-002 – Exclusive Tax, No Voucher, C&C, Registered
**Steps**: Add product B (VAT excl) → login → C&C → EFT.  
**Expected**: Tax added after subtotal; no shipping; EFT success.

### TC-CK-003 – Free Shipping Threshold (Boundary)
**Data**: Cart = T−0.01, T, T+0.01.  
**Expected**: Shipping charged only below threshold; waived at/above.

### TC-CK-004 – Percent Voucher Edges
**Data**: 0%, 100%, >100%.  
**Expected**: 0% = no effect, 100% = min payable, >100% rejected.

### TC-CK-005 – Fixed Voucher Edges
**Data**: 0, 0.01, equal subtotal, greater than subtotal.  
**Expected**: Proper floors and informative messages.

### TC-CK-006 – Expired / Not Active Voucher
**Expected**: Error shown; totals unchanged; EFT still possible.

### TC-CK-007 – Non‑Stackable with Product Discount
**Expected**: Voucher blocked; message shown; proceed possible.

### TC-CK-008 – Invalid Voucher Format
**Data**: Too short/long/invalid chars.  
**Expected**: Validation error.

### TC-CK-009 – Address Validation (Delivery)
**Expected**: Inline errors for missing/invalid address fields.

### TC-CK-010 – EFT Cancel / Timeout
**Steps**: Start EFT → cancel/timeout.  
**Expected**: Cart preserved; retry possible.

### TC-CK-011 – Concurrency: Last Stock Item
**Pre**: Stock = 1.  
**Steps**: Two customers attempt simultaneously.  
**Expected**: One succeeds, one fails gracefully.

### TC-CK-012 – Pairwise Minimal Coverage
**Expected**: Cover all factor pairs with minimal test set.

### TC-CK-013 – Back‑and‑Forth Edits
**Steps**: Change qty/address/payment mid‑flow.  
**Expected**: Recalculated totals preserved.

### TC-CK-014 – Mixed Tax Items
**Expected**: Both VAT incl/excl items handled correctly; totals accurate.

### TC-CK-015 – Session Expiry During Checkout
**Expected**: Session timeout handled; user re‑auths without data loss.

### TC-CK-016 – Mobile vs Desktop Parity
**Expected**: Flows consistent; UI copy differences acceptable.

---

## 6) Notes
- All checkout scenarios **must use EFT**.  
- Merchant dashboard and Usersite tested on **Desktop & Mobile**.  
- Domain used: **http://ameratesting.uat.boxcommerce.dev/**  
- Test data (products, categories, vouchers) prepared by tester.
