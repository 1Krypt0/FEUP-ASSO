/** @type {import('tailwindcss').Config} */

module.exports = {
	content: ['./src/**/*.{html,js,svelte,ts}'],
	darkMode: 'class',
	theme: {
		extend: {
			colors: {
				lights: {
					50: '#fffbeb',
					300: '#fcd34d'
				},
				media: {
					50: '#ecfeff',
					300: '#67e8f9'
				},
				climate: {
					50: '#f0fdf4',
					300: '#86efac'
				}
			},
			backgroundImage: {
				lights: "url('$lib/assets/bg-lights.svg')",
				media: "url('$lib/assets/bg-media.svg')",
				climate: "url('$lib/assets/bg-climate.svg')"
			}
		}
	},
	plugins: []
};
