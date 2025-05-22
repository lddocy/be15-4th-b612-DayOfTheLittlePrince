export default {
    content: [
        './index.html',
        './src/**/*.{vue,js,ts,jsx,tsx}',
    ],
    theme: {
        extend: {
            fontFamily: {
                cafe24: ['Cafe24Oneprettynight', 'sans-serif'],
            },
            colors: {
                'dlp_purple': '#8F87F1',
                'dlp_lavender': '#C68EFD',
                'dlp_pinkPurple': '#E9A5F1',
                'dlp_pink': '#FED2E2',
                'dlp_yellow': '#FFFCCB',
                'dlp_white40': 'rgba(253, 254, 254, 0.4)',
                'dlp_button-primary': 'var(--dlp-color-bg-button-primary)',

                // 투두리스트 박스 색상
                dlp_card: '#C9C3E3',        // 기본 카드 배경
                dlp_card_hover: '#A49CAC'   // Hover 할 시 색깔
            },
            fontSize: {
                'dlp_title-lg': 'var(--dlp-font-size-title-lg)',
                'dlp_title-md': 'var(--dlp-font-size-title-md)',
                'dlp_text-lg': 'var(--dlp-font-size-text-lg)',
                'dlp_text-md': 'var(--dlp-font-size-text-md)',
                'dlp_text-sm': 'var(--dlp-font-size-text-sm)',
                'dlp_primary-button': 'var(--dlp-btn-primary-font-size)',
                'dlp_button': 'var(--dlp-font-size-button)',
            },
        },
    },
    safelist: [
        {
            pattern: /bg-dlp_card\/40/,
            variants: ['hover']
        },
        {
            pattern: /bg-dlp_card_hover\/80/,
            variants: ['hover']
        },
        'bg-event-1',
        'bg-event-2',
        'bg-event-3',
        'bg-event-4',
        'bg-event-5',
    ],
    plugins: [],
}
