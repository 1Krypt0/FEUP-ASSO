import { sveltekit } from '@sveltejs/kit/vite';
import { defineConfig } from 'vitest/config';
import { SvelteKitPWA } from '@vite-pwa/sveltekit';

export default defineConfig({
	plugins: [
		sveltekit(),
		SvelteKitPWA({
			scope: '/',
			base: '/',
			injectManifest: {
				globPatterns: ['**/*.{js,ts,css,html,svg,png,ico}']
			},
			registerType: 'autoUpdate',
			manifest: {
				background_color: '#ffffff',
				theme_color: '#ffffff',
				name: 'Home by Iota',
				short_name: 'Home',
				start_url: '/',
				display: 'standalone',
				icons: [
					{
						src: 'pwa-192x192.png',
						sizes: '192x192',
						type: 'image/png'
					},
					{
						src: 'pwa-512x512.png',
						sizes: '512x512',
						type: 'image/png'
					},
					{
						src: 'pwa-512x512.png',
						sizes: '512x512',
						type: 'image/png',
						purpose: 'any maskable'
					}
				]
			}
		})
	],
	test: {
		include: ['src/**/*.{test,spec}.{js,ts}']
	}
});
