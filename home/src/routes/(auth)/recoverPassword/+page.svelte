<script lang="ts">
	import { createForm } from 'svelte-forms-lib';

	const { form, errors, handleChange, handleSubmit } = createForm({
		initialValues: {
			email: ''
		},
		validate: (values) => {
			const errors = {
				email: ''
			};

			if (!values.email) errors.email = 'Email is required';

			return errors;
		},
		onSubmit: (values) => {
			// TODO: complete recover password
			console.log(values);
		}
	});
</script>

<div class="flex justify-center items-center w-full">
	<form on:submit|preventDefault={handleSubmit} class="flex flex-col sm:items-end">
		<h1 class="text-4xl font-bold pb-8">
			<span>Recover your </span><span class="text-primary">Home</span>
		</h1>

		<formgroup class="py-2 flex flex-col sm:flex-row sm:items-center">
			<label class="sm:mr-4" for="email">Email</label>
			<input
				class="focus:outline-none rounded-[20px] shadow-md py-1.5 px-2.5"
				type="email"
				id="email"
				on:change={handleChange}
				bind:value={$form.email}
			/>
		</formgroup>
		{#if $errors.email}
			<p class="text-xs text-red-600 font-semibold">{$errors.email}</p>
		{/if}

		<div class="mt-12">
			<button type="submit" class="bg-primary text-white font-bold rounded-full py-3 px-8"
				>Recover Password</button
			>
		</div>
	</form>
</div>
