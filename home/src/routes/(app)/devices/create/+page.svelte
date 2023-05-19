<script lang="ts">
	import { goto } from '$app/navigation';
	import { page } from '$app/stores';
	import type { PageData } from './$types';

	export let data: PageData;

	const roomID = Number.parseInt($page.url.searchParams.get('roomID') ?? '-1');
	const categoryID = $page.url.searchParams.get('category') ?? '';

	let step = 0;
	let formData = {
		name: '',
		roomID: roomID,
		categoryID: categoryID,
		selectedName: ''
	};

	async function submit() {
		const response = await fetch('/devices/create', {
			method: 'POST',
			body: JSON.stringify(formData)
		});

		if (response.ok) {
			goto('/');
		}
	}
</script>

<section class="px-5 w-full py-4 md:px-28 md:py-4">
	<h1 class="font-bold md:pl-20 pt-12 text-center md:text-start text-5xl">Add a device</h1>
	<section class="w-full h-full flex items-center justify-center">
		<form
			method="POST"
			on:submit|preventDefault={submit}
			class="flex flex-col justify-center gap-6"
		>
			{#if step === 0}
				<div class="pl-5">
					<label for="device-name">Name</label>
					<input
						bind:value={formData.name}
						id="device-name"
						name="name"
						type="text"
						placeholder="Enter the new device name here"
						class="py-3 px-2 md:px-5 rounded-full mt-5  sm:ml-5"
					/>
				</div>

				<div class="sm:flex sm:items-center pl-5">
					<label for="device-room">Room</label>
					<select
						class="flex py-3 mt-5 sm:ml-5 sm:mt-0 rounded-full text-center w-full bg-white"
						id="device-room"
						name="room"
						bind:value={formData.roomID}
					>
						{#each data.rooms as room}
							<option value={room.id}>{room.name}</option>
						{/each}
					</select>
				</div>

				<div class="sm:flex sm:items-center pl-5">
					<label for="device-category">Category</label>
					<select
						class="flex py-3 mt-5 sm:ml-5 sm:mt-0 rounded-full text-center w-full bg-white"
						id="device-category"
						name="category"
						bind:value={formData.categoryID}
					>
						{#each data.categories as category}
							<option value={category.id}>{category.name}</option>
						{/each}
					</select>
				</div>
				<button
					type="button"
					class="self-end px-8 mx-5 text-center text-white font-bold my-12 py-3 rounded-full bg-primary"
					on:click={() => step++}>Search</button
				>
			{:else if step === 1}
				<h2 class="font-bold text-center md:text-start text-3xl">Search Results</h2>

				<section
					class="flex flex-col items-center gap-6 max-h-56 md:max-h-72 lg:max-h-96 overflow-auto py-6"
				>
					{#each data.foundDevices as result}
						<button
							type="button"
							class="bg-white px-5 drop-shadow-lg rounded-full text-center text-lg py-3 w-full focus:bg-accent focus:text-white"
							on:click={() => (formData.selectedName = result.name)}
						>
							{result.name}
						</button>
					{/each}
				</section>
				<button
					type="submit"
					class="self-end px-8 mx-5 text-center text-white font-bold my-6 py-3 rounded-full bg-primary"
					>Create</button
				>
			{/if}
		</form>
	</section>
</section>
