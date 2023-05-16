<script lang="ts">
	export let name: string;
	export let id: number;
</script>

<section class="flex flex-col drop-shadow-lg  bg-white rounded-3xl">
	<div class="text-xl py-6 px-8 flex gap-6 items-center ">
		<slot />
		<p>{name}</p>
	</div>
	<a
		href="/device/{id}"
		class="self-end px-8 mx-5 text-center text-white font-bold my-6  py-3 rounded-full bg-primary"
		>See more</a
	>
</section>
