<script lang="ts">
	import type { PageData, ActionData } from './$types';

	export let data: PageData;
	export let form: ActionData;
</script>

<div class="px-5 py-4 w-full md:px-28 md:py-4">
	<h1 class="font-bold md:pl-20 pt-12 text-center md:text-start text-5xl">Edit {data.name}</h1>
	<section class="w-full h-full flex items-center justify-center">
		<form method="post" class="flex flex-col gap-6 justify-center">
			<div class="pl-5">
				<label for="device-name">Name</label>
				<input
					id="device-name"
					name="name"
					type="text"
					placeholder="Enter the new Room name here"
					value={data.name}
					class="py-3 px-2 md:px-5 rounded-full mt-5 sm:ml-5"
				/>
			</div>
			{#if form?.missing}
				<p class="pt-2 pr-8">Name field is required</p>
			{/if}

			<div class="sm:flex sm:items-center pl-5">
				<label for="device-room">Room</label>
				<select
					class="flex items-center py-3 mt-5 sm:mt-0 sm:ml-5 rounded-full text-center w-full bg-white"
					id="device-room"
					name="room"
				>
					{#each data.rooms as room}
						<option selected={data.roomID == room.id} value={room.id}>{room.name}</option>
					{/each}
				</select>
			</div>

			<button
				type="submit"
				class="self-end px-8 mx-5 text-center text-white font-bold my-12 py-3 rounded-full bg-primary"
				>Edit</button
			>
		</form>
	</section>
</div>
