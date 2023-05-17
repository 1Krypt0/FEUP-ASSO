<script lang="ts">
	import type { PageData, ActionData } from './$types';

	export let data: PageData;
	export let form: ActionData;
</script>

<div class="flex flex-col w-full">
	<h1 class="font-bold pl-20 pt-12 text-5xl">Edit {data.name}</h1>
	<section class="w-full h-full">
		<form method="post" class="w-1/4 h-2/3 mx-auto flex flex-col gap-6 items-center justify-center">
			<div class="flex items-center w-full">
				<label for="device-name" class="pr-5">Name</label>
				<input
					id="device-name"
					name="name"
					type="text"
					placeholder="Enter the new Room name here"
					value={data.name}
					class="py-3 px-5 w-full rounded-full"
				/>
			</div>
			{#if form?.missing}
				<p class="pt-2 pr-8">Name field is required</p>
			{/if}

			<div class="flex items-center w-full">
				<label for="device-room" class="pr-5">Room</label>
				<select
					class="flex py-3 rounded-full text-center w-full bg-white"
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
