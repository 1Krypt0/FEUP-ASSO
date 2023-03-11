/** @type {import('tailwindcss').Config} */

module.exports = {
	content: ['./src/**/*.{html,js,svelte,ts}'],
	darkMode: 'class',
	theme: {
		extend: {
			colors: {
				lights: {
					50: '#fffbeb',
					400: '#fbbf24',
					500: '#f59e0b'
				},
				media: {
					50: '#ecfeff',
					400: '#22d3ee',
					500: '#06b6d4'
				},
				climate: {
					50: '#f0fdf4',
					400: '#4ade80',
					500: '#22c55e'
				}
			}
		}
	},
	plugins: []
};
