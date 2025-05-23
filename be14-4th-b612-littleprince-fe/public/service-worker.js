self.addEventListener("push", function (event) {
    try {
        const rawText = event.data?.text();
        console.log("📨 푸시 원문:", rawText);

        const data = JSON.parse(rawText);
        console.log("📨 파싱된 데이터:", data);

        event.waitUntil(
            self.registration.showNotification("🌟 알림 도착!", {
                body: data.body, // ✅ 이 부분이 백엔드 message
                icon: "/icon.png", // 필요시 변경
            })
        );
    } catch (e) {
        console.error("❌ Push 알림 처리 중 오류:", e);
        event.waitUntil(
            self.registration.showNotification("🌟 알림 도착!", {
                body: "알림을 표시할 수 없습니다.",
                icon: "/icon.png",
            })
        );
    }
});
