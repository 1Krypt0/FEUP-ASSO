<script lang="ts">
	import { page } from '$app/stores';

	export let data;

	const type = $page.params.page;
	let name = '';
	let description = '';
	let macAddress = '';

	const device = type.charAt(0).toUpperCase() + type.slice(1);

	function addDevice() {}
</script>

<svelte:head>
	<title>Home by Iota - Add {device}</title>
	<meta name="description" content="Add {device} Page" />
</svelte:head>

<section class="flex justify-center items-center w-full h-full">
	<form on:submit|preventDefault={addDevice} class="flex flex-col sm:items-end">
		<h1 class="text-4xl font-bold pb-8">Add a device</h1>

		<formgroup class="py-2 flex flex-col sm:flex-row sm:items-center">
			<label class="sm:mr-4" for="name">Name</label>
			<input
				class="focus:outline-none rounded-[20px] shadow-md py-1.5 px-2.5"
				type="text"
				id="name"
				bind:value={name}
			/>
		</formgroup>

		<formgroup class="py-2 flex flex-col sm:flex-row sm:items-center">
			<label class="sm:mr-4" for="description">Description</label>
			<input
				class="focus:outline-none rounded-[20px] shadow-md py-1.5 px-2.5"
				type="text"
				id="description"
				bind:value={description}
			/>
		</formgroup>

		<formgroup class="py-2 flex flex-col sm:flex-row sm:items-center">
			<label class="sm:mr-4" for="macaddr">MAC Address</label>
			<input
				class="focus:outline-none rounded-[20px] shadow-md py-1.5 px-2.5"
				type="text"
				id="macaddr"
				bind:value={macAddress}
			/>
		</formgroup>

		<div class="flex justify-end w-full mt-4">
			<button
				type="submit"
				class="{data.colors[type][400]} hover:{data.colors[
					type
				][500]} text-white font-bold rounded-full py-2 px-4">Add</button
			>
		</div>
	</form>
</section>
