<script lang="ts">
	import { createForm } from 'svelte-forms-lib';

	const { form, errors, handleChange, handleSubmit } = createForm({
		initialValues: {
			email: '',
			password: ''
		},
		validate: (values) => {
			const errors = {
				email: '',
				password: ''
			};

			if (!values.email) errors.email = 'Email is required';
			if (!values.password) errors.password = 'Password is required';

			return errors;
		},
		onSubmit: (values) => {
			// TODO: complete login
			console.log(values);
		}
	});
</script>

<div class="flex justify-center items-center w-full">
	<form on:submit|preventDefault={handleSubmit} class="flex flex-col sm:items-end">
		<h1 class="text-4xl font-bold pb-8">
			<span>Login to your </span><span class="text-primary">Home</span>
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

		<formgroup class="py-2 flex flex-col sm:flex-row sm:items-center">
			<label class="sm:mr-4" for="password">Password</label>
			<input
				class="focus:outline-none rounded-[20px] shadow-md py-1.5 px-2.5"
				type="password"
				id="password"
				on:change={handleChange}
				bind:value={$form.password}
			/>
		</formgroup>
		{#if $errors.password}
			<p class="text-xs text-red-600 font-semibold">{$errors.password}</p>
		{/if}

		<div class="mt-2">
			<a href="/recoverPassword" class="underline text-gray-400 hover:text-gray-500"
				>Forgot your password?</a
			>
		</div>

		<div class="mt-12">
			<span
				>Do not have an account yet? <a
					href="/register"
					class="underline text-gray-400 hover:text-gray-500 mr-4">Register</a
				></span
			>
			<button type="submit" class="bg-primary text-white font-bold rounded-full py-3 px-8"
				>Login</button
			>
		</div>
	</form>
</div>
