<script lang="ts">
	import svelte_logo from '$lib/images/svelte-logo.svg';
	import down_arrow from '$lib/assets/chevron-down.svg';
	import moon from '$lib/assets/moon.svg';
	import sun from '$lib/assets/sun.svg';
	export let darkMode = false;
</script>

<section class="flex gap-4">
	<img class="w-12 h-12" alt="user profile" src={svelte_logo} />
	<img alt="w-12 h-12" src={down_arrow} />
	<img alt="Dark/Light Mode toggle" src={darkMode ? sun : moon} />
</section>
